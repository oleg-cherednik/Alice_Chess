package ru.olegcherednik.alice.chess.visualization.unicode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.olegcherednik.alice.chess.Piece;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.PiecePrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnicodePiecePrintStrategy implements PiecePrintStrategy {

    public static final UnicodePiecePrintStrategy INSTANCE = new UnicodePiecePrintStrategy();

    @Override
    public void print(Piece piece, PrintStream out) {
        if (piece == Piece.EMPTY)
            out.print("\u2001");
        else if (piece.getColor() == Player.Color.BLACK)
            out.print(piece.getType().getUnicodeBlack());
        else if (piece.getColor() == Player.Color.WHITE)
            out.print(piece.getType().getUnicodeWhite());
        else
            out.print("\u2001");
    }
}
