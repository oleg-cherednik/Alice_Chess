package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class King extends AbstractPiece {

    public King(PieceId id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        return addMove(true, context);
    }

    @Override
    public Set<String> getNextEatCellIds(GameContext context) {
        return addMove(false, context);
    }

    private Set<String> addMove(boolean checkProtection, GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addMove(cellIds, 0, -1, checkProtection, context);
        addMove(cellIds, 0, 1, checkProtection, context);
        addMove(cellIds, -1, 0, checkProtection, context);
        addMove(cellIds, 1, 0, checkProtection, context);
        addMove(cellIds, -1, -1, checkProtection, context);
        addMove(cellIds, -1, 1, checkProtection, context);
        addMove(cellIds, 1, -1, checkProtection, context);
        addMove(cellIds, 1, 1, checkProtection, context);
        return cellIds;
    }

    /**
     * King cannot move to the cell if it's protected by any of other player's piece.
     * <ul>
     * King can move only if:
     * <li>cell is empty</li>
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private void addMove(Set<String> cellIds, int incCol, int incRow, boolean checkProtection, GameContext context) {
        Board board = context.getBoard();
        Board.Cell toCell = board.getCell(col + incCol, row + incRow);
        Player.Color currentPlayer = context.getCurrentPlayer();

        if (toCell.isNull())
            return;
        if (checkProtection && !toCell.isNeutralOrProtectedBy(currentPlayer))
            return;
        if (toCell.isEmpty() || toCell.getPiece().getColor() != currentPlayer)
            cellIds.add(toCell.getId());
    }

}
