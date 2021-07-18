package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public final class Board {

    private final Cell[][] board = {
            {
                    Cell.createTaken(Piece.createBlack(Piece.Type.ROOK)), Cell.createTaken(Piece.createBlack(Piece.Type.KNIGHT)),
                    Cell.createTaken(Piece.createBlack(Piece.Type.BISHOP)), Cell.createTaken(Piece.createBlack(Piece.Type.QUEEN)),
                    Cell.createTaken(Piece.createBlack(Piece.Type.KING)), Cell.createTaken(Piece.createBlack(Piece.Type.BISHOP)),
                    Cell.createTaken(Piece.createBlack(Piece.Type.KNIGHT)), Cell.createTaken(Piece.createBlack(Piece.Type.ROOK))
            },
            {
                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)),
                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)),
                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)),
                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN))
            },
            {
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
            },
            {
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
            },
            {
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
            },
            {
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
            },
            {
                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)),
                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)),
                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)),
                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN))
            },
            {
                    Cell.createTaken(Piece.createWhite(Piece.Type.ROOK)), Cell.createTaken(Piece.createWhite(Piece.Type.KNIGHT)),
                    Cell.createTaken(Piece.createWhite(Piece.Type.BISHOP)), Cell.createTaken(Piece.createWhite(Piece.Type.QUEEN)),
                    Cell.createTaken(Piece.createWhite(Piece.Type.KING)), Cell.createTaken(Piece.createWhite(Piece.Type.BISHOP)),
                    Cell.createTaken(Piece.createWhite(Piece.Type.KNIGHT)), Cell.createTaken(Piece.createWhite(Piece.Type.ROOK))
            }
    };

    public int getHeight() {
        return board.length;
    }

    public int getWidth(int row) {
        return board[row].length;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Cell {

        private Piece piece;

        public static Cell createEmpty() {
            return new Cell(Piece.EMPTY);
        }

        public static Cell createTaken(Piece piece) {
            return new Cell(piece);
        }

    }

}
