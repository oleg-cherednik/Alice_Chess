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
public final class Cell {

    private static final Map<String, Cell> CACHE = new HashMap<>();

    private final Board.Row row;
    private final Board.Column col;
    private final String id;

    public static Cell create(Board.Row row, Board.Column col) {
        return CACHE.computeIfAbsent(calcId(row, col), id -> new Cell(row, col, id));
    }

    private static String calcId(Board.Row row, Board.Column col) {
        return Character.toString(row.getId()) + col.getId();
    }

    @Override
    public String toString() {
        return id;
    }

}
