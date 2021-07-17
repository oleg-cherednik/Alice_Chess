package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@RequiredArgsConstructor
public final class Piece implements Printable {

    private final Type type;

    @Override
    public void print(PrintStream out) {
        out.print(type.ch);
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {
        EMPTY(' '),
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
