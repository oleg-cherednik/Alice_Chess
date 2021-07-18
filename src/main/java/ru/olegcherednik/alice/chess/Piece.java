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

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Id {
        ROOK_A(Type.ROOK, 0),
        KNIGHT_B(Type.KNIGHT, 1),
        BISHOP_C(Type.BISHOP, 2),
        QUEEN_D(Type.QUEEN, 3),
        KING_E(Type.KING, 4),
        BISHOP_F(Type.BISHOP, 5),
        KNIGHT_G(Type.KNIGHT, 6),
        ROOK_H(Type.ROOK, 7),
        PAWN_A(Type.PAWN, 0),
        PAWN_B(Type.PAWN, 1),
        PAWN_C(Type.PAWN, 2),
        PAWN_D(Type.PAWN, 3),
        PAWN_E(Type.PAWN, 4),
        PAWN_F(Type.PAWN, 5),
        PAWN_G(Type.PAWN, 6),
        PAWN_H(Type.PAWN, 7);

        private final Type type;
        private final int col;

        public Piece create(Player.Color color) {
            return new Piece(this, color);
        }
    }
}
