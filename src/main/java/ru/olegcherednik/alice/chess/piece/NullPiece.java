package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.GameContext;

import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 20.07.2021
 */
final class NullPiece extends AbstractPiece {

    NullPiece() {
        super(null, null);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        return Set.of();
    }

    @Override
    public String toString() {
        return "<null>";
    }

}
