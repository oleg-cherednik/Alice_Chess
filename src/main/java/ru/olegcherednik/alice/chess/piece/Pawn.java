package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.NotImplementedException;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class Pawn extends AbstractPiece {

    public Pawn(PieceId id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getAvailableMoves(GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addOneRowMove(cellIds, context);
        addTwoRowsMove(cellIds, context);
        addDiagonalMove(cellIds, -1, context);
        addDiagonalMove(cellIds, 1, context);
        return cellIds;
    }

    /**
     * <ul>
     * +1 row can be add only if:
     * <li>cell is empty</li>
     * </ul>
     */
    private void addOneRowMove(Set<String> cellIds, GameContext context) {
        int rowInc = getRowInc(context);
        Board.Cell toCell = context.getBoard().getCell(col, row + rowInc);

        if (toCell.isEmpty())
            cellIds.add(toCell.getId());
    }

    /**
     * <ul>
     * +2 row can be add only if:
     * <li>pawn was not moved yet</li>
     * <li>cell +1 is empty</li>
     * <li>cell +2 is empty</li>
     * </ul>
     */
    private void addTwoRowsMove(Set<String> cellIds, GameContext context) {
        if (totalMoves != 0)
            return;

        int rowInc = getRowInc(context);
        Board.Cell toCell1 = context.getBoard().getCell(col, row + rowInc);
        Board.Cell toCell2 = context.getBoard().getCell(col, row + rowInc + rowInc);

        if (toCell1.isEmpty() && toCell2.isEmpty())
            cellIds.add(toCell2.getId());
    }

    /**
     * <ul>
     * diagonal can be add only if:
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private void addDiagonalMove(Set<String> cellIds, int colInc, GameContext context) {
        int rowInc = getRowInc(context);
        Player.Color player = context.getCurrentPlayer();
        Board.Cell toCell = context.getBoard().getCell(col + colInc, row + rowInc);

        if (toCell.isEmpty())
            return;
        if (toCell.getPiece().getColor() == player)
            return;

        cellIds.add(toCell.getId());
    }

    // TODO add Promotion
    // TODO add En passant

    private static int getRowInc(GameContext context) {
        Player.Color currentPlayer = context.getCurrentPlayer();

        if (currentPlayer == Player.Color.WHITE)
            return context.whitePlayerBottom() ? -1 : 1;
        if (currentPlayer == Player.Color.BLACK)
            return context.whitePlayerBottom() ? 1 : -1;

        throw new NotImplementedException(String.format("Unknown player '%s'", currentPlayer.getTitle()));
    }

}
