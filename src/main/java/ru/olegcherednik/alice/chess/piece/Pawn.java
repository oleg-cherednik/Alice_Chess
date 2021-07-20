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
    public Set<String> getNextMoveCellIds(GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addOneRowMove(cellIds, context);
        addTwoRowsMove(cellIds, context);
        cellIds.addAll(getNextEatCellIds(context));
        return cellIds;
    }

    @Override
    public Set<String> getNextEatCellIds(GameContext context) {
        Set<String> cellIds = new HashSet<>();
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
        int incRow = getIncRow(context);
        Board.Cell toCell = context.getBoard().getCell(col, row + incRow);

        if (toCell.isNull())
            return;
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

        int incRow = getIncRow(context);
        Board.Cell toCell1 = context.getBoard().getCell(col, row + incRow);
        Board.Cell toCell2 = context.getBoard().getCell(col, row + incRow + incRow);

        if (toCell1.isNull() || !toCell1.isEmpty())
            return;
        if (toCell2.isNull() || !toCell2.isEmpty())
            return;

        cellIds.add(toCell2.getId());
    }

    /**
     * <ul>
     * diagonal can be add only if:
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private void addDiagonalMove(Set<String> cellIds, int incCol, GameContext context) {
        int incRow = getIncRow(context);
        Player.Color currentPlayer = context.getCurrentPlayer();
        Board.Cell toCell = context.getBoard().getCell(row + incRow, col + incCol);

        if (toCell.isNull() || toCell.isEmpty())
            return;
        if (toCell.getPiece().getColor() == currentPlayer)
            return;

        cellIds.add(toCell.getId());
    }

    // TODO add Promotion
    // TODO add En passant

    private static int getIncRow(GameContext context) {
        Player.Color currentPlayer = context.getCurrentPlayer();

        if (currentPlayer == Player.Color.WHITE)
            return context.whitePlayerBottom() ? -1 : 1;
        if (currentPlayer == Player.Color.BLACK)
            return context.whitePlayerBottom() ? 1 : -1;

        throw new NotImplementedException(String.format("Unknown player '%s'", currentPlayer.getTitle()));
    }

}
