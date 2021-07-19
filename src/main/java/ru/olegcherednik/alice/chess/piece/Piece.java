package ru.olegcherednik.alice.chess.piece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Collections;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public interface Piece {

    Piece NULL = new Piece() {
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

    default Piece.Type getType() {
        return getId().getType();
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    enum Type {
        ROOK("Rook", 'r', "♖", "♜"),
        KNIGHT("Knight", 'n', "♘", "♞"),
        BISHOP("Bishop", 'b', "♗", "♝"),
        QUEEN("Queen", 'q', "♕", "♛"),
        KING("King", 'k', "♔", "♚"),
        PAWN("Pawn", 'p', "♙", "♟");

        private final String title;
        private final char ascii;
        private final String unicodeWhite;
        private final String unicodeBlack;

    }

}
