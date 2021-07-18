package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.List;
import java.util.Optional;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public final class Board {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private final Cell[][] cells = createCells();
//            {
//                    Cell.createTaken(Piece.createBlack(Piece.Type.ROOK)), Cell.createTaken(Piece.createBlack(Piece.Type.KNIGHT)),
//                    Cell.createTaken(Piece.createBlack(Piece.Type.BISHOP)), Cell.createTaken(Piece.createBlack(Piece.Type.QUEEN)),
//                    Cell.createTaken(Piece.createBlack(Piece.Type.KING)), Cell.createTaken(Piece.createBlack(Piece.Type.BISHOP)),
//                    Cell.createTaken(Piece.createBlack(Piece.Type.KNIGHT)), Cell.createTaken(Piece.createBlack(Piece.Type.ROOK))
//            },
//            {
//                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)),
//                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)),
//                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)),
//                    Cell.createTaken(Piece.createBlack(Piece.Type.PAWN)), Cell.createTaken(Piece.createBlack(Piece.Type.PAWN))
//            },
//            {
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
//            },
//            {
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
//            },
//            {
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
//            },
//            {
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(),
//                    Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty(), Cell.createEmpty()
//            },
//            {
//                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)),
//                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)),
//                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)),
//                    Cell.createTaken(Piece.createWhite(Piece.Type.PAWN)), Cell.createTaken(Piece.createWhite(Piece.Type.PAWN))
//            },
//            {
//                    Cell.createTaken(Piece.createWhite(Piece.Type.ROOK)), Cell.createTaken(Piece.createWhite(Piece.Type.KNIGHT)),
//                    Cell.createTaken(Piece.createWhite(Piece.Type.BISHOP)), Cell.createTaken(Piece.createWhite(Piece.Type.QUEEN)),
//                    Cell.createTaken(Piece.createWhite(Piece.Type.KING)), Cell.createTaken(Piece.createWhite(Piece.Type.BISHOP)),
//                    Cell.createTaken(Piece.createWhite(Piece.Type.KNIGHT)), Cell.createTaken(Piece.createWhite(Piece.Type.ROOK))
//            }
//    };

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
        List<Piece> rooks = player.getPieces(Piece.Type.ROOK);
        List<Piece> knights = player.getPieces(Piece.Type.KNIGHT);
        List<Piece> bishops = player.getPieces(Piece.Type.BISHOP);
        List<Piece> queens = player.getPieces(Piece.Type.QUEEN);
        List<Piece> kings = player.getPieces(Piece.Type.KING);
        List<Piece> pawns = player.getPieces(Piece.Type.PAWN);

        cells[mainRow][0].setPiece(rooks.get(0));
        cells[mainRow][1].setPiece(knights.get(0));
        cells[mainRow][2].setPiece(bishops.get(0));
        cells[mainRow][3].setPiece(queens.get(0));
        cells[mainRow][4].setPiece(kings.get(0));
        cells[mainRow][5].setPiece(bishops.get(1));
        cells[mainRow][6].setPiece(knights.get(1));
        cells[mainRow][7].setPiece(rooks.get(1));

        for (int i = 0; i < 8; i++)
            cells[pawnsRow][i].setPiece(pawns.get(i));
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
            return new Cell(Piece.EMPTY);
        }

        public static Cell createTaken(Piece piece) {
            return new Cell(piece);
        }

        public void setPiece(Piece piece) {
            this.piece = Optional.ofNullable(piece).orElse(Piece.EMPTY);
        }

    }

}
