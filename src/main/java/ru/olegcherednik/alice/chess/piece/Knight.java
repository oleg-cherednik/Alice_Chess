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
final class Knight extends AbstractPiece {

    public Knight(Id id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        return Stream.of(
                getMoveCellId(-1, -2, context),
                getMoveCellId(1, -2, context),
                getMoveCellId(-1, 2, context),
                getMoveCellId(1, 2, context),
                getMoveCellId(-2, -1, context),
                getMoveCellId(2, -1, context),
                getMoveCellId(-2, 1, context),
                getMoveCellId(2, 1, context))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toSet());
    }

    /**
     * <ul>
     * Knight can move only if:
     * <li>cell is empty</li>
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private String getMoveCellId(int incCol, int incRow, GameContext context) {
        Player.Color currentPlayer = context.getCurrentPlayer().getColor();
        Board.Cell toCell = context.getBoard().getCell(col + incCol, row + incRow);

        if (toCell.isNull())
            return null;
        if (toCell.isEmpty())
            return toCell.getId();
        if (toCell.getPiece().getColor() != currentPlayer)
            return toCell.getId();
        return null;
    }

}
