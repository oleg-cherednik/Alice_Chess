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
final class Bishop extends AbstractPiece {

    public Bishop(PieceId id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getAvailableMoves(GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addDiagonalMove(cellIds, -1, -1, context);
        addDiagonalMove(cellIds, -1, 1, context);
        addDiagonalMove(cellIds, 1, -1, context);
        addDiagonalMove(cellIds, 1, 1, context);
        return cellIds;
    }

    /**
     * <ul>
     * Bishop can move only if:
     * <li>cell is empty</li>
     * <li>cell is taken by other player's piece and this is the first such cell</li>
     * <li>all previous cells are empty</li>
     * </ul>
     */
    private void addDiagonalMove(Set<String> cellIds, int incCol, int incRow, GameContext context) {
        int row = this.row;
        int col = this.col;

        do {
            row += incRow;
            col += incCol;
            Board.Cell toCell = context.getBoard().getCell(col, row);

            if (toCell.isNull())
                break;
            if (toCell.isEmpty())
                cellIds.add(toCell.getId());
            else {
                Player.Color currentPlayer = context.getCurrentPlayer();

                if (toCell.getPiece().getColor() != currentPlayer)
                    cellIds.add(toCell.getId());

                break;
            }
        } while (true);
    }

}
