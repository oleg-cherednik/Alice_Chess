package ru.olegcherednik.alice.chess.piece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @Override
    public String toString() {
        return id + "_" + color + '_' + Processor.getCellId(col, row);
    }

}
