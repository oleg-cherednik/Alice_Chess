package ru.olegcherednik.alice.chess.piece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.move.Processor;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Collections;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class AbstractPiece implements Piece {

    protected final PieceId id;
    protected final Player.Color color;
    protected int totalMoves;
    protected int col = -1;
    protected int row = -1;

    @Override
    public Set<String> getAvailableMoves(GameContext context) {
        return Collections.emptySet();
    }

    @Override
    public void moveTo(String toCellId) {
        if (col != -1 && row != -1)
            totalMoves++;

        col = Processor.getCellCol(toCellId);
        row = Processor.getCellRow(toCellId);
    }

    /**
     * <ul>
     * Piece can move only if:
     * <li>cell is empty</li>
     * <li>cell is taken by other player's piece and this is the first such cell</li>
     * <li>all previous cells are empty</li>
     * </ul>
     */
    protected void addMultiMove(Set<String> cellIds, int incCol, int incRow, GameContext context) {
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

    @Override
    public String toString() {
        return id + "_" + color + '_' + Processor.getCellId(col, row);
    }

}
