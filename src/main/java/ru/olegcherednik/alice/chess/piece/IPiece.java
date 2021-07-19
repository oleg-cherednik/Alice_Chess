package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Collections;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public interface IPiece {

    IPiece NULL = new IPiece() {
        @Override
        public PieceId getId() {
            return null;
        }

        @Override
        public Player.Color getColor() {
            return null;
        }

        @Override
        public Set<String> getAvailableMoves(GameContext context) {
            return Collections.emptySet();
        }

        @Override
        public void moveTo(String toCellId) {

        }

        @Override
        public String toString() {
            return "<null>";
        }
    };

    PieceId getId();

    Player.Color getColor();

    Set<String> getAvailableMoves(GameContext context);

    void moveTo(String toCellId);

    default PieceType getType() {
        return getId().getType();
    }

}
