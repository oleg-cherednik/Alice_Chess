package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.olegcherednik.alice.chess.move.Processor;
import ru.olegcherednik.alice.chess.piece.Piece;
import ru.olegcherednik.alice.chess.player.Player;

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

    private static Cell[][] createCells() {
        Cell[][] cells = new Cell[WIDTH][HEIGHT];

        for (int row = 0; row < WIDTH; row++)
            for (int col = 0; col < HEIGHT; col++)
                cells[row][col] = Cell.createEmpty(Processor.getCellId(col, row));

        return cells;
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
            piece.moveTo(Processor.getCellId(col, row));
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
        int row = Processor.getCellRow(cellId);
        int col = Processor.getCellCol(cellId);

        if (row < 0 || row >= cells.length)
            return Cell.NULL;
        if (col < 0 || col >= cells[row].length)
            return Cell.NULL;

        return cells[row][col];
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Cell {

        public static final Cell NULL = createEmpty("----");

        /** Format is D5 or A1 */
        private final String id;
        private Piece piece;

        public static Cell createEmpty(String id) {
            return new Cell(id, Piece.NULL);
        }

        public boolean isNull() {
            return this == NULL;
        }

        public boolean isEmpty() {
            return piece == Piece.NULL;
        }

        public void clear() {
            setPiece(null);
        }

        public void setPiece(Piece piece) {
            if (this != NULL)
                this.piece = Optional.ofNullable(piece).orElse(Piece.NULL);
        }

        @Override
        public String toString() {
            return id + (isEmpty() ? "" : " (" + piece + ')');
        }

    }

}
