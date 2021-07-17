package ru.olegcherednik.alice.chess.visualization;

import ru.olegcherednik.alice.chess.Board;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public interface BoardPrintStrategy {

    void print(Board board, PrintStream out);
}
