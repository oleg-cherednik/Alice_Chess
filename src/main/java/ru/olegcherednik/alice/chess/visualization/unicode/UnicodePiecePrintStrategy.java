package ru.olegcherednik.alice.chess.visualization.unicode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.olegcherednik.alice.chess.Game;
import ru.olegcherednik.alice.chess.Piece;
import ru.olegcherednik.alice.chess.visualization.PiecePrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnicodePiecePrintStrategy implements PiecePrintStrategy, UnicodePrintStrategy {

    public static final UnicodePiecePrintStrategy INSTANCE = new UnicodePiecePrintStrategy();

    @Override
    public void print(Piece piece, PrintStream out) {
        if (piece == Piece.EMPTY)
            out.print(LONG_SPACE);
        else if (piece.getTeam() == Game.Team.BLACK)
            out.print(piece.getType().getUnicodeBlack());
        else if (piece.getTeam() == Game.Team.WHITE)
            out.print(piece.getType().getUnicodeWhite());
        else
            out.print(LONG_SPACE);
    }
}
