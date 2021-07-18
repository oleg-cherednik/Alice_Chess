package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public final class Board {

    private final Cell[][] board = {
            {
                    new Cell(new Piece(Piece.Type.ROOK, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.KNIGHT, Game.Team.BLACK)),
                    new Cell(new Piece(Piece.Type.BISHOP, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.QUEEN, Game.Team.BLACK)),
                    new Cell(new Piece(Piece.Type.KING, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.BISHOP, Game.Team.BLACK)),
                    new Cell(new Piece(Piece.Type.KNIGHT, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.ROOK, Game.Team.BLACK))
            },
            {
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)),
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)),
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)),
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.BLACK))
            },
            {
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell()
            },
            {
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell()
            },
            {
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell()
            },
            {
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell(),
                    new Cell(), new Cell()
            },
            {
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)),
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)),
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)),
                    new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.PAWN, Game.Team.WHITE))
            },
            {
                    new Cell(new Piece(Piece.Type.ROOK, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.KNIGHT, Game.Team.WHITE)),
                    new Cell(new Piece(Piece.Type.BISHOP, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.QUEEN, Game.Team.WHITE)),
                    new Cell(new Piece(Piece.Type.KING, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.BISHOP, Game.Team.WHITE)),
                    new Cell(new Piece(Piece.Type.KNIGHT, Game.Team.WHITE)), new Cell(new Piece(Piece.Type.ROOK, Game.Team.WHITE))
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
    public static final class Cell {

        private Piece piece;

        public Cell() {
            this(Piece.EMPTY);
        }

        public Cell(Piece piece) {
            this.piece = piece;
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Row {
        ROW_A('A'),
        ROW_B('B'),
        ROW_C('C'),
        ROW_D('D'),
        ROW_E('E'),
        ROW_F('F'),
        ROW_G('G'),
        ROW_H('H');

        @Getter
        private final char id;
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Column {
        COL_1('1'),
        COL_2('2'),
        COL_3('3'),
        COL_4('4'),
        COL_5('5'),
        COL_6('6'),
        COL_7('7'),
        COL_8('8');

        @Getter
        private final char id;
    }


}
