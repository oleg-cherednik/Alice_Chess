package ru.olegcherednik.alice.chess.visualization.ascii;

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
public final class AsciiCellPrintStrategy implements CellPrintStrategy, AsciiPrintStrategy {

    public static final AsciiCellPrintStrategy INSTANCE = new AsciiCellPrintStrategy();

    @Override
    public void print(Board.Cell cell, PrintStream out) {
        AsciiPiecePrintStrategy.INSTANCE.print(cell.getPiece(), out);
    }
}
