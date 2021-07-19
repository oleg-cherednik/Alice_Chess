package ru.olegcherednik.alice.chess.visualization;

import ru.olegcherednik.alice.chess.piece.Piece;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public interface PiecePrintStrategy {

    void print(Piece piece, PrintStream out);
}
