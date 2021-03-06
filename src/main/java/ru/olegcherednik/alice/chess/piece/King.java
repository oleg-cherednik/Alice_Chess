package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class King extends AbstractPiece {

    public King(Id id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        return getMoves(true, context);
    }

    @Override
    public Set<String> getNextEatCellIds(GameContext context) {
        return getMoves(false, context);
    }

    private Set<String> getMoves(boolean checkCellPressure, GameContext context) {
        return Stream.of(
                getMoveCellId(0, -1, checkCellPressure, context),
                getMoveCellId(0, 1, checkCellPressure, context),
                getMoveCellId(-1, 0, checkCellPressure, context),
                getMoveCellId(1, 0, checkCellPressure, context),
                getMoveCellId(-1, -1, checkCellPressure, context),
                getMoveCellId(-1, 1, checkCellPressure, context),
                getMoveCellId(1, -1, checkCellPressure, context),
                getMoveCellId(1, 1, checkCellPressure, context))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toSet());
    }

    /**
     * King cannot move to the cell if it's protected by any of other player's piece.
     * <ul>
     * King can move only if:
     * <li>cell is empty</li>
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private String getMoveCellId(int incCol, int incRow, boolean checkCellPressure, GameContext context) {
        Board board = context.getBoard();
        Board.Cell toCell = board.getCell(col + incCol, row + incRow);

        if (toCell.isNull())
            return null;
        if (checkCellPressure && !toCell.isNeutralOrUnderPressureBy(color))
            return null;
        if (toCell.isEmpty() || toCell.getPiece().getColor() != color)
            return toCell.getId();
        return null;
    }

}
