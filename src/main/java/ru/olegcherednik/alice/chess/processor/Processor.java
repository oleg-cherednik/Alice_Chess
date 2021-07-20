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

    public void doNextPly(GameContext context) {
        Ply ply = getNextPly(context);
        updateCurrentPlayerPieces(ply, context);
        movePiece(ply, context);
        updateCurrentPlayerCellProtection(context);
        checkEndgame();
        moveToNextPlayer();
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
            cell.clearProtection();

        for (Piece piece : currentPlayer.getPieces())
            for (String cellId : piece.getNextEatCellIds(context))
                board.getCell(cellId).setProtectedBy(currentPlayer.getColor());
    }

    private void checkEndgame() {
        // TODO king cannot move
        // TODO check check
        // TODO check mate
    }

    private void moveToNextPlayer() {
        if (currentPlayer == playerWhite)
            currentPlayer = playerBlack;
        else if (currentPlayer == playerBlack)
            currentPlayer = playerWhite;
        else
            throw new NotImplementedException(String.format("Unknown player '%s'", currentPlayer));

        // two plies are equal to one move; the While goes first, so change move number on each white's ply
        if (currentPlayer.getColor() == Player.Color.WHITE)
            moveNo++;
    }

}
