package ru.olegcherednik.alice.chess.visualization.unicode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.visualization.CellPrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnicodeCellPrintStrategy implements CellPrintStrategy, UnicodePrintStrategy {

    public static final UnicodeCellPrintStrategy INSTANCE = new UnicodeCellPrintStrategy();

    @Override
    public void print(Board.Cell cell, PrintStream out) {
        UnicodePiecePrintStrategy.INSTANCE.print(cell.getPiece(), out);
    }
}
