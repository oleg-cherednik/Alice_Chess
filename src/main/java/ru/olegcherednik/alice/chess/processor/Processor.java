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
    /** Check for a king is a case when king's cell is under pressure and king has available moves */
    @Getter
    private boolean checkKing;
    /** Mate for a king is a case when king's cell is under pressure and king has not available moves */
    private boolean mateKing;
    // TODO implement stalemate
//    private boolean stalemateKing;

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
        checkSpecialCases(context);
        printInfoMessage(context);

        boolean gameOver = isGameOver();

        if (!gameOver)
            moveToNextPlayer();

        return gameOver;
    }

    private Ply getNextPly(GameContext context) {
        context.getOut().format("Move %d (%s) > ", moveNo + 1, currentPlayer.getColor().getTitle());

        String strPly = Board.Cell.normalizeStrPly(currentPlayer.nextPly(context));
        Board.Cell fromCell = context.getBoard().getCell(Ply.getFromCellId(strPly));
        Ply ply = Ply.createFromStr(strPly, moveNo, currentPlayer.getColor());
        validationProcessor.validate(strPly, context);

        // TODO real rule is that next move should avoid check king case (right now only king itself can move)
        if (!fromCell.isNull() && context.isCheckKing() && fromCell.getPiece().getType() != Piece.Type.KING)
            throw new ChessException(String.format("Under the Check only '%s' can move", Piece.Type.KING.getTitle()));

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

    // TODO right now when check only king can move; do add new check that next move avoids check to the king
    private void checkSpecialCases(GameContext context) {
        checkKing = false;
        mateKing = false;

        Player opponentPlayer = getOpponentPlayer();
        Piece king = opponentPlayer.getPiece(Piece.Id.KING_E);

        // not a mate when king's cell is not under the opponent player pressure
        if (!king.isUnderPressure(context))
            return;

        checkKing = true;
        mateKing = king.getNextMoveCellIds(context).isEmpty();
    }

    private void printInfoMessage(GameContext context) {
        if (!checkKing)
            return;
        if (mateKing)
            context.getOut().format("Check and Mate to '%s' king\n", context.getOpponentPlayer());
        else
            context.getOut().format("Check to '%s' king\n", context.getOpponentPlayer());
    }

    private void moveToNextPlayer() {
        currentPlayer = getOpponentPlayer();

        // two plies are equal to one move; the While goes first, so change move number on each white's ply
        if (currentPlayer.getColor() == Player.Color.WHITE)
            moveNo++;
    }

    private boolean isGameOver() {
        return mateKing;
    }

    public Player getOpponentPlayer() {
        if (currentPlayer == playerWhite)
            return playerBlack;
        if (currentPlayer == playerBlack)
            return playerWhite;
        throw new NotImplementedException(String.format("Unknown player '%s'", currentPlayer));
    }

}
