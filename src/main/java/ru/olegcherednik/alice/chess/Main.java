package ru.olegcherednik.alice.chess;

import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.unicode.UnicodeBoardPrintStrategy;

import java.io.UnsupportedEncodingException;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public class Main {

    public static void main(String... args) throws UnsupportedEncodingException {
        Board board = new Board();
//        BoardPrintStrategy boardPrintStrategy = AsciiBoardPrintStrategy.INSTANCE;
        BoardPrintStrategy boardPrintStrategy = UnicodeBoardPrintStrategy.INSTANCE;
        boardPrintStrategy.print(board, System.out);
    }

}
