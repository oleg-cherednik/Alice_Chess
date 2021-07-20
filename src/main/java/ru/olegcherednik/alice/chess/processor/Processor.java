package ru.olegcherednik.alice.chess.processor;

import lombok.Getter;
import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.exceptions.NotImplementedException;
import ru.olegcherednik.alice.chess.piece.Piece;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.processor.validations.ValidationProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Chess processor is responsible for game logic independently from game mechanic like move pieces on the board.
 *
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public final class Processor {

    private final List<Ply> plies = new ArrayList<>();
    private final ValidationProcessor validationProcessor = new ValidationProcessor();
    private final Player playerWhite;
    private final Player playerBlack;
    @Getter
    private Player currentPlayer;
    @Getter
    private int moveNo;

    public Processor(Player playerWhite, Player playerBlack, Player currentPlayer) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.currentPlayer = currentPlayer;
    }

    /**
     * Do next step (Ply) for the {@code Processor#currentPlayer} and retrieve {@literal true} in case of game should be finished.
     *
     * @return {@literal true} in case of game should be finished
     */
    public boolean doNextPlyAndFinishGame(GameContext context) {
        Ply ply = getNextPly(context);
        updateCurrentPlayerPieces(ply, context);
        movePiece(ply, context);
        updateCurrentPlayerCellProtection(context);

        if (isGameFinished(context))
            return true;

        checkEndgame();
        moveToNextPlayer();

        return false;
    }

    private Ply getNextPly(GameContext context) {
        context.getOut().format("Move %d (%s) > ", moveNo + 1, currentPlayer.getColor().getTitle());

        String strPly = Board.Cell.normalizeStrPly(currentPlayer.nextPly(context));
        validationProcessor.validate(strPly, context);

        Ply ply = Ply.createFromStr(strPly, moveNo, currentPlayer.getColor());
        plies.add(ply);

        return ply;
    }

    private void updateCurrentPlayerPieces(Ply ply, GameContext context) {
        Board board = context.getBoard();
        String toCellId = ply.getToCellId();
        Board.Cell toCell = board.getCell(toCellId);

        if (toCell.isNull() || toCell.isEmpty())
            return;

        Piece.Id pieceId = toCell.getPiece().getId();

        if (!currentPlayer.removePiece(pieceId))
            throw new ChessException(String.format("Player '%s' does not have piece '%s' to remove",
                    currentPlayer, pieceId));
    }

    private static void movePiece(Ply ply, GameContext context) {
        String fromCellId = ply.getFromCellId();
        String toCellId = ply.getToCellId();
        context.getBoard().movePiece(fromCellId, toCellId);
    }

    private void updateCurrentPlayerCellProtection(GameContext context) {
        Board board = context.getBoard();

        for (Board.Cell cell : board.getAllCells())
            cell.clearUnderPressure();

        for (Piece piece : currentPlayer.getPieces())
            for (String cellId : piece.getNextEatCellIds(context))
                board.getCell(cellId).setUnderPressureBy(currentPlayer.getColor());
    }

    private boolean isGameFinished(GameContext context) {
        if (checkMatePosition(context))
            return true;

        return false;
    }

    /** Mate for a king is possible when king's cell is under the pressure of the opponent player and king does not have available moves */
    private boolean checkMatePosition(GameContext context) {
        Player opponentPlayer = getOpponentPlayer();
        Piece king = opponentPlayer.getPiece(Piece.Id.KING_E);
        Board.Cell cell = context.getBoard().getCell(king.getCellId());

        // not a mate when king's cell is not under the opponent player pressure
        if (!cell.isUnderPressureBy(currentPlayer.getColor()))
            return false;

        Set<String> cellIds = king.getNextMoveCellIds(context);

        if (cellIds.isEmpty()) {
            context.getOut().format("Mate to '%s' king\n", opponentPlayer);
            return true;
        }

        return false;
    }

    private void checkEndgame() {
        // TODO king cannot move
        // TODO check check
        // TODO check mate
    }

    private void moveToNextPlayer() {
        currentPlayer = getOpponentPlayer();

        // two plies are equal to one move; the While goes first, so change move number on each white's ply
        if (currentPlayer.getColor() == Player.Color.WHITE)
            moveNo++;
    }

    private Player getOpponentPlayer() {
        if (currentPlayer == playerWhite)
            return playerBlack;
        if (currentPlayer == playerBlack)
            return playerWhite;
        throw new NotImplementedException(String.format("Unknown player '%s'", currentPlayer));
    }
}
