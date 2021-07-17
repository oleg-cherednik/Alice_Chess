package ru.olegcherednik.alice.chess;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public class Main {

    private static final String LINE = "+---+---+---+---+---+---+---+---+";
    private static final String ROW_LETTERS = "A   B   C   D   E   F   G   H";

    public static void main(String... args) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println("\u0006");

        System.out.format("  %s\n", LINE);

        for (int i = 0; i < 8; i++) {
            System.out.format("%d |", 8 - i);

            for (int j = 0; j < 8; j++) {
                System.out.format(" %c |", (char)6);
            }

            System.out.format("\n  %s\n", LINE);
        }

        System.out.format("    %s\n", ROW_LETTERS);
    }

//    public void print(){
//        System.out.print("   (#1)(#2)(#3)(#4)(#5)(#6)(#7)(#8)");
//        for(int i = 0 ; i < 8; i++ ) {
//            for (int j = 0; j < 8; j++) {
//                if (j == 0) {
//                    System.out.println();
//                    System.out.print("(" + Character.toString((char) (i+65)) + ")");
//                }
//                if (!Board.getInstance().getTiles()[i][j].isOccupied()) {
//                    System.out.print("[ ]");
//                } else {
//                    System.out.print("["+ PieceSymbolFactory
//                            .getSymbol(Board.getInstance().getTiles()[i][j].getPiece()) + "]");
//                }
//            }
//        }
//    }

//    static class PieceSymbolFactory {
//        private static final int whiteKing = 2654;
//        private static final int blackKing = 0x265A;
//
//        static char getSymbol(Piece piece) {
//            if(piece instanceof King){
//                if(piece.getColor() == PieceColor.WHITE) {
//                    return (char)(Integer.parseInt(String.valueOf(whiteKing),16));
//                } else {
//                    return (char)(Integer.parseInt(String.valueOf(blackKing)));
//                }
//            }
//        }

}
