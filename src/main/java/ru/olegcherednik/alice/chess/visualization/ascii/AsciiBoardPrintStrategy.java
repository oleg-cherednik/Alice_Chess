package ru.olegcherednik.alice.chess.visualization.ascii;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AsciiBoardPrintStrategy implements BoardPrintStrategy {

    public static final AsciiBoardPrintStrategy INSTANCE = new AsciiBoardPrintStrategy();

    private static final String LINE = "+---+---+---+---+---+---+---+---+";
    private static final String ROW_LETTERS = "A   B   C   D   E   F   G   H";

    @Override
    public void print(Board board, PrintStream out) {
        out.format("  %s\n", LINE);

        for (int row = 0; row < board.getHeight(); row++) {
            out.format("%d |", board.getHeight() - row);

            for (int col = 0; col < board.getWidth(row); col++) {
                Board.Cell cell = board.getCell(row, col);
                out.print(' ');
                AsciiCellPrintStrategy.INSTANCE.print(cell, out);
                out.print(" |");
            }

            if (row < board.getHeight() - 1)
                out.format("\n  %s", LINE);

            out.println();
        }

        out.format("  %s\n", LINE);
        out.format("    %s\n", ROW_LETTERS);
    }
}
