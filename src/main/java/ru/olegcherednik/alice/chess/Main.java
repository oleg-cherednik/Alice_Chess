package ru.olegcherednik.alice.chess;

import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.UnicodeBoardPrintStrategy;

import java.io.UnsupportedEncodingException;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public class Main {

    public static void main(String... args) throws UnsupportedEncodingException {
        Board board = new Board();
//        BoardPrintStrategy boardPrintStrategy = LetterBoardPrintStrategy.INSTANCE;
        BoardPrintStrategy boardPrintStrategy = UnicodeBoardPrintStrategy.INSTANCE;
        boardPrintStrategy.print(board, System.out);
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
