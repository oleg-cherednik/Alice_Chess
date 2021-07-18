package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.player.Player;

/**
 * Class implements one piece on the chess board.
 *
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@Getter
@RequiredArgsConstructor
public final class Piece {

    public static final Piece NULL = new Piece(null, null);

    private final Id id;
    private final Player.Color color;

    public Piece.Type getType() {
        return id.type;
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {
        ROOK('r', "\u2656", "\u265C"),
        KNIGHT('n', "\u2658", "\u265E"),
        BISHOP('b', "\u2657", "\u265D"),
        QUEEN('q', "\u2655", "\u265B"),
        KING('k', "\u2654", "\u265A"),
        PAWN('p', "\u2659", "\u265F");

        private final char ascii;
        private final String unicodeWhite;
        private final String unicodeBlack;

    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Id {
        ROOK_A(Type.ROOK),
        KNIGHT_B(Type.KNIGHT),
        BISHOP_C(Type.BISHOP),
        QUEEN_D(Type.QUEEN),
        KING_E(Type.KING),
        BISHOP_F(Type.BISHOP),
        KNIGHT_G(Type.KNIGHT),
        ROOK_H(Type.ROOK),
        PAWN_A(Type.PAWN),
        PAWN_B(Type.PAWN),
        PAWN_C(Type.PAWN),
        PAWN_D(Type.PAWN),
        PAWN_E(Type.PAWN),
        PAWN_F(Type.PAWN),
        PAWN_G(Type.PAWN),
        PAWN_H(Type.PAWN);

        private final Type type;

        public Piece create(Player.Color color) {
            return new Piece(this, color);
        }
    }
}
