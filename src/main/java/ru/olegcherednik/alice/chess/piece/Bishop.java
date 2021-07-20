package ru.olegcherednik.alice.chess.piece;

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
    public Set<String> getNextMoveCellIds(GameContext context) {
        Set<String> cellIds = new HashSet<>();
        addMultiMove(cellIds, -1, -1, context);
        addMultiMove(cellIds, -1, 1, context);
        addMultiMove(cellIds, 1, -1, context);
        addMultiMove(cellIds, 1, 1, context);
        return cellIds;
    }

}
