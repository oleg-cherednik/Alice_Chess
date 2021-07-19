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
    public Set<String> getAvailableMoves(GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addMove(cellIds, 0, -1, context);
        addMove(cellIds, 0, 1, context);
        addMove(cellIds, -1, 0, context);
        addMove(cellIds, 1, 0, context);
        addMove(cellIds, -1, -1, context);
        addMove(cellIds, -1, 1, context);
        addMove(cellIds, 1, -1, context);
        addMove(cellIds, 1, 1, context);
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
    private void addMove(Set<String> cellIds, int incCol, int incRow, GameContext context) {
        Board board = context.getBoard();
        Board.Cell toCell = board.getCell(col + incCol, row + incRow);

        if (toCell.isNull())
            return;
        if (isCellEnemyProtected(toCell, context))
            return;
        if (toCell.isEmpty())
            cellIds.add(toCell.getId());
    }

    private static boolean isCellEnemyProtected(Board.Cell toCell, GameContext context) {
        Player.Color currentPlayer = context.getCurrentPlayer();
        Board board = context.getBoard();

        for (Board.Cell cell : board.getCells()) {
            if (cell.isEmpty())
                continue;
            if (cell.getPiece().getColor() == currentPlayer)
                continue;

            Set<String> availableToCellIds = cell.getPiece().getAvailableMoves(context);

            if (availableToCellIds.contains(toCell.getId()))
                return true;
        }

        return false;
    }

}
