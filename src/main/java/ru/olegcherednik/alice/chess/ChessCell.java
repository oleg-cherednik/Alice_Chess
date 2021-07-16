package ru.olegcherednik.alice.chess;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Cherednik
 * @since 17.07.2021
 */
@EqualsAndHashCode
@RequiredArgsConstructor
public final class ChessCell {

    private static final Map<String, ChessCell> CACHE = new HashMap<>();

    private final ChessBoard.Row row;
    private final ChessBoard.Column col;
    private final String id;

    public static ChessCell create(ChessBoard.Row row, ChessBoard.Column col) {
        return CACHE.computeIfAbsent(calcId(row, col), id -> new ChessCell(row, col, id));
    }

    private static String calcId(ChessBoard.Row row, ChessBoard.Column col) {
        return Character.toString(row.getId()) + col.getId();
    }

    @Override
    public String toString() {
        return id;
    }

}
