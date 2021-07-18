package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Optional;

/**
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
                cells[row][col] = Cell.createEmpty();

        return cells;
    }

    public Board(Player topPlayer, Player bottomPlayer) {
        addPlayerPieces(topPlayer, 0, 1);
        addPlayerPieces(bottomPlayer, 7, 6);
    }

    private void addPlayerPieces(Player player, int mainRow, int pawnsRow) {
        for (Piece.Id id : Piece.Id.values()) {
            int row = id.getType() == Piece.Type.PAWN ? pawnsRow : mainRow;
            int col = id.getCol();
            cells[row][col].setPiece(player.getPiece(id));
        }
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Cell {

        private Piece piece;

        public static Cell createEmpty() {
            return new Cell(Piece.NULL);
        }

        public static Cell createTaken(Piece piece) {
            return new Cell(piece);
        }

        public void setPiece(Piece piece) {
            this.piece = Optional.ofNullable(piece).orElse(Piece.NULL);
        }

    }

}
