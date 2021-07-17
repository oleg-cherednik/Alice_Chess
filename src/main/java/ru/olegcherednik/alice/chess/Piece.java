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
        else
            out.print(team.apply(type.ch));
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {
        ROOK('r'),
        KNIGHT('n'),
        BISHOP('b'),
        QUEEN('q'),
        KING('k'),
        PAWN('p');

        private final char ch;

        public Set<Cell> nextMoves(Cell cell) {
            return null;
        }
    }
}
