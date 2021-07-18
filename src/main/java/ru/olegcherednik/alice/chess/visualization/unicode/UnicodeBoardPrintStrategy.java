package ru.olegcherednik.alice.chess.visualization.unicode;

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
public final class UnicodeBoardPrintStrategy implements BoardPrintStrategy {

    public static final UnicodeBoardPrintStrategy INSTANCE = new UnicodeBoardPrintStrategy();

    private static final String TOP_LINE = "┌─—─┬─—─┬─—─┬─—─┬─—─┬─—─┬─—─┬─—─┐";
    private static final String MIDDLE_LINE = "├─—─┼─—─┼─—─┼─—─┼─—─┼─—─┼─—─┼─—─┤";
    private static final String BOTTOM_LINE = "└─—─┴─—─┴─—─┴─—─┴─—─┴─—─┴─—─┴─—─┘";
    private static final String ROW_LETTERS = "A\u2001  B\u2001  C\u2001  D\u2001  E\u2001  F\u2001  G\u2001  H";

    @Override
    public void print(Board board, PrintStream out) {
        out.format("  %s\n", TOP_LINE);

        for (int row = 0; row < board.getHeight(); row++) {
            out.format("%d │", board.getHeight() - row);

            for (int col = 0; col < board.getWidth(); col++) {
                Board.Cell cell = board.getCell(row, col);
                out.print(' ');
                UnicodeCellPrintStrategy.INSTANCE.print(cell, out);
                out.print(" │");
            }

            if (row < board.getHeight() - 1)
                out.format("\n  %s", MIDDLE_LINE);

            out.println();
        }

        out.format("  %s\n", BOTTOM_LINE);
        out.format("   %s\n", ROW_LETTERS);
    }
}
