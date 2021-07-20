package ru.olegcherednik.alice.chess;

import lombok.Getter;
import lombok.Setter;
import ru.olegcherednik.alice.chess.move.Processor;
import ru.olegcherednik.alice.chess.piece.Piece;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Class represents a chess board and all related mechanic activities (i.g. move a piece from one cell to another)
 *
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public final class Board {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private final Cell[][] cells = createCells();
    @Getter
    private final List<Cell> allCells = buildCellsList(cells);

    private static Cell[][] createCells() {
        Cell[][] cells = new Cell[WIDTH][HEIGHT];

        for (int row = 0; row < WIDTH; row++)
            for (int col = 0; col < HEIGHT; col++)
                cells[row][col] = Cell.createEmpty(Board.Cell.getCellId(col, row));

        return cells;
    }

    private static List<Cell> buildCellsList(Cell[][] cells) {
        List<Cell> res = new ArrayList<>();

        for (int row = 0; row < cells.length; row++)
            for (int col = 0; col < cells[row].length; col++)
                res.add(cells[row][col]);

        return Collections.unmodifiableList(res);
    }

    public Board(Player topPlayer, Player bottomPlayer) {
        addPlayerPieces(topPlayer, 0, 1);
        addPlayerPieces(bottomPlayer, 7, 6);
    }

    private void addPlayerPieces(Player player, int mainRow, int pawnsRow) {
        for (Piece.PieceId id : Piece.PieceId.values()) {
            int row = id.getType() == Piece.Type.PAWN ? pawnsRow : mainRow;
            int col = id.getInitCol();
            Piece piece = player.getPiece(id);
            cells[row][col].setPiece(piece);
            piece.moveTo(Board.Cell.getCellId(col, row));
        }
    }

    public void movePiece(String fromCellId, String toCellId) {
        Board.Cell cellFrom = getCell(fromCellId);
        Board.Cell cellTo = getCell(toCellId);
        Piece piece = cellFrom.getPiece();

        cellTo.setPiece(piece);
        cellFrom.clear();
        piece.moveTo(cellTo.getId());
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public Cell getCell(int col, int row) {
        if (row < 0 || row >= cells.length)
            return Cell.NULL;
        if (col < 0 || col >= cells[row].length)
            return Cell.NULL;
        return cells[row][col];
    }

    public Cell getCell(String cellId) {
        int row = Board.Cell.getCellRow(cellId);
        int col = Board.Cell.getCellCol(cellId);

        if (row < 0 || row >= cells.length)
            return Cell.NULL;
        if (col < 0 || col >= cells[row].length)
            return Cell.NULL;

        return cells[row][col];
    }

    @Getter
    public static final class Cell {

        public static final Cell NULL = createEmpty("----");

        /** Format is D5 or A1 */
        private final String id;
        private Piece piece;
        @Setter
        private Player.Color protectedBy;

        public static Cell createEmpty(String id) {
            return new Cell(id, Piece.NULL);
        }

        private Cell(String id, Piece piece) {
            this.id = id;
            this.piece = piece;
        }

        public boolean isNull() {
            return this == NULL;
        }

        public boolean isEmpty() {
            return piece == Piece.NULL;
        }

        public void clearProtection() {
            protectedBy = null;
        }

        public boolean isNeutralOrProtectedBy(Player.Color player) {
            return protectedBy == null || protectedBy == player;
        }

        public void clear() {
            setPiece(null);
        }

        public void setPiece(Piece piece) {
            if (this != NULL)
                this.piece = Optional.ofNullable(piece).orElse(Piece.NULL);
        }

        public static String normalizeStrPly(String strPly) {
            return strPly.toLowerCase().trim();
        }

        public static int getCellRow(String cellId) {
            return '8' - cellId.charAt(1);
        }

        public static int getCellCol(String cellId) {
            return cellId.charAt(0) - 'a';
        }

        public static String getCellId(int col, int row) {
            return (char)('a' + col) + String.valueOf(8 - row);
        }

        @Override
        public String toString() {
            return id + (isEmpty() ? "" : " (" + piece + ')');
        }

    }

}
