package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public final class Board implements Printable {

    private final Cell[][] board = {
            {
                    new Cell(new Piece(Piece.Type.ROOK)), new Cell(new Piece(Piece.Type.KNIGHT)), new Cell(new Piece(Piece.Type.BISHOP)),
                    new Cell(new Piece(Piece.Type.QUEEN)), new Cell(new Piece(Piece.Type.KING)), new Cell(new Piece(Piece.Type.BISHOP)),
                    new Cell(new Piece(Piece.Type.KNIGHT)), new Cell(new Piece(Piece.Type.ROOK))
            },
            {
                    new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)),
                    new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)),
                    new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN))
            },
            {
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY))
            },
            {
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY))
            },
            {
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY))
            },
            {
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY)),
                    new Cell(new Piece(Piece.Type.EMPTY)), new Cell(new Piece(Piece.Type.EMPTY))
            },
            {
                    new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)),
                    new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN)),
                    new Cell(new Piece(Piece.Type.PAWN)), new Cell(new Piece(Piece.Type.PAWN))
            },
            {
                    new Cell(new Piece(Piece.Type.ROOK)), new Cell(new Piece(Piece.Type.KNIGHT)), new Cell(new Piece(Piece.Type.BISHOP)),
                    new Cell(new Piece(Piece.Type.QUEEN)), new Cell(new Piece(Piece.Type.KING)), new Cell(new Piece(Piece.Type.BISHOP)),
                    new Cell(new Piece(Piece.Type.KNIGHT)), new Cell(new Piece(Piece.Type.ROOK))
            }
    };

    private static final String TOP_LINE = "╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗";
    private static final String BOTTOM_LINE = "╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝";

    private static final String LINE = "\u2554\u2013\u2014\u2013+-\u2014-+-\u2014-+-\u2014-+-\u2014-+-\u2014-+-\u2014-+-\u2014-+";
    private static final String ROW_LETTERS = "A\u2001  B\u2001  C\u2001  D\u2001  E\u2001  F\u2001  G\u2001  H";

    @Override
    public void print(PrintStream out) {
//        System.out.format("  %s\n", TOP_LINE);

        for (int row = 0; row < board.length; row++) {
            out.format("%d ║", board.length - row);
                                           // "\u2659"
            for (int col = 0; col < board[row].length; col++) {
                Cell cell = board[row][col];
                out.print(' ');
                cell.print(out);
                out.print(" |");
            }

            //            System.out.format("\n  %s\n", LINE);
            out.println();
        }

        System.out.format("\n  %s\n", BOTTOM_LINE);
        System.out.format("   %s\n", ROW_LETTERS);
    }

    @RequiredArgsConstructor
    public static final class Cell implements Printable {

        private final Piece piece;

        @Override
        public void print(PrintStream out) {
            piece.print(out);
        }
    }

    public enum Team {
        BLACK,
        WHITE
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
