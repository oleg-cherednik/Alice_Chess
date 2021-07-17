package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum Piece {
    ROOK('r'),
    KNIGHT('n'),
    BISHOP('b'),
    QUEEN('q'),
    KING('k'),
    PAWN('p');

    private final char id;

    public Set<Cell> nextMoves(Cell cell) {
        return null;
    }

}
