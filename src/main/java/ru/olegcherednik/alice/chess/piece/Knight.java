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
final class Knight extends AbstractPiece {

    public Knight(PieceId id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addMove(cellIds, -1, -2, context);
        addMove(cellIds, 1, -2, context);
        addMove(cellIds, -1, 2, context);
        addMove(cellIds, 1, 2, context);
        addMove(cellIds, -2, -1, context);
        addMove(cellIds, 2, -1, context);
        addMove(cellIds, -2, 1, context);
        addMove(cellIds, 2, 1, context);
        return cellIds;
    }

    /**
     * <ul>
     * Knight can move only if:
     * <li>cell is empty</li>
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private void addMove(Set<String> cellIds, int incCol, int incRow, GameContext context) {
        Player.Color currentPlayer = context.getCurrentPlayer();
        Board.Cell toCell = context.getBoard().getCell(col + incCol, row + incRow);

        if (toCell.isNull())
            return;
        if (toCell.isEmpty())
            cellIds.add(toCell.getId());
        else if (toCell.getPiece().getColor() != currentPlayer)
            cellIds.add(toCell.getId());
    }

}
