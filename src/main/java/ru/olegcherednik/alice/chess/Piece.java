package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * Class implements one piece on the chess board.
 *
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@Getter
@RequiredArgsConstructor
public final class Piece {

    public static final Piece EMPTY = new Piece(null, null);

    private final Type type;
    private final Game.Team team;

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

        public Set<Cell> nextMoves(Cell cell) {
            return null;
        }
    }
}
