package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Set;

/**
 * Class implements one piece on the chess board.
 *
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@RequiredArgsConstructor
public final class Piece implements Printable {

    public static final Piece EMPTY = new Piece(null, null);

    private final Type type;
    private final Game.Team team;

    @Override
    public void print(PrintStream out) {
        if (this == EMPTY)
            out.print(' ');
        else if (team == Game.Team.BLACK)
            out.print(Character.toUpperCase(type.ch));
        else if (team == Game.Team.WHITE)
            out.print(Character.toLowerCase(type.ch));
        else
            out.print(' ');
    }

    @Override
    public void printUtf8(PrintStream out) {
        if (this == EMPTY)
            out.print('\u2001');
        else if (team == Game.Team.BLACK)
            out.print(type.black);
        else if (team == Game.Team.WHITE)
            out.print(type.white);
        else
            out.print('\u2001');
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {
        ROOK('r', "\u2656", "\u265C"),
        KNIGHT('n', "\u2658", "\u265E"),
        BISHOP('b', "\u2657", "\u265D"),
        QUEEN('q', "\u2655", "\u265B"),
        KING('k', "\u2654", "\u265A"),
        PAWN('p', "\u2659", "\u265F");

        private final char ch;
        private final String white;
        private final String black;

        public Set<Cell> nextMoves(Cell cell) {
            return null;
        }
    }
}
