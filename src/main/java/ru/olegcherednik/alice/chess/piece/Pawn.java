package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.NotImplementedException;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class Pawn extends AbstractPiece {

    public Pawn(Id id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        return Stream.of(
                addOneRowMoveCellId(context),
                addTwoRowsMoveCellId(context),
                addDiagonalMoveCellId(-1, context),
                addDiagonalMoveCellId(1, context))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getNextEatCellIds(GameContext context) {
        return Stream.of(
                addEatMoveCellId(-1, context),
                addEatMoveCellId(1, context))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toSet());
    }

    /**
     * <ul>
     * +1 row can be add only if:
     * <li>cell is empty</li>
     * </ul>
     */
    private String addOneRowMoveCellId(GameContext context) {
        int incRow = getIncRow(context);
        Board.Cell toCell = context.getBoard().getCell(col, row + incRow);

        if (toCell.isNull())
            return null;
        if (toCell.isEmpty())
            return toCell.getId();
        return null;
    }

    /**
     * <ul>
     * +2 row can be add only if:
     * <li>pawn was not moved yet</li>
     * <li>cell +1 is empty</li>
     * <li>cell +2 is empty</li>
     * </ul>
     */
    private String addTwoRowsMoveCellId(GameContext context) {
        if (totalMoves != 0)
            return null;

        int incRow = getIncRow(context);
        Board.Cell toCell1 = context.getBoard().getCell(col, row + incRow);
        Board.Cell toCell2 = context.getBoard().getCell(col, row + incRow + incRow);

        if (toCell1.isNull() || !toCell1.isEmpty())
            return null;
        if (toCell2.isNull() || !toCell2.isEmpty())
            return null;
        return toCell2.getId();
    }

    /**
     * <ul>
     * diagonal can be add only if:
     * <li>cell is taken by other player's piece</li>
     * </ul>
     */
    private String addDiagonalMoveCellId(int incCol, GameContext context) {
        int incRow = getIncRow(context);
        Player.Color currentPlayer = context.getCurrentPlayer().getColor();
        Board.Cell toCell = context.getBoard().getCell(col + incCol, row + incRow);

        if (toCell.isNull() || toCell.isEmpty())
            return null;
        if (toCell.getPiece().getColor() == currentPlayer)
            return null;
        return toCell.getId();
    }

    // TODO add Promotion
    // TODO add En passant

    private String addEatMoveCellId(int incCol, GameContext context) {
        int incRow = getIncRow(context);
        Player.Color currentPlayer = context.getCurrentPlayer().getColor();
        Board.Cell toCell = context.getBoard().getCell(col + incCol, row + incRow);

        if (toCell.isNull())
            return null;
        if (toCell.isEmpty() || toCell.getPiece().getColor() != currentPlayer)
            return toCell.getId();
        return null;
    }

    private static int getIncRow(GameContext context) {
        Player.Color currentPlayer = context.getCurrentPlayer().getColor();

        if (currentPlayer == Player.Color.WHITE)
            return context.whitePlayerBottom() ? -1 : 1;
        if (currentPlayer == Player.Color.BLACK)
            return context.whitePlayerBottom() ? 1 : -1;

        throw new NotImplementedException(String.format("Unknown player '%s'", currentPlayer.getTitle()));
    }

}
