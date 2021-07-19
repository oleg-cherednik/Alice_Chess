package ru.olegcherednik.alice.chess.visualization.ascii;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.olegcherednik.alice.chess.piece.IPiece;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.PiecePrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AsciiPiecePrintStrategy implements PiecePrintStrategy {

    public static final AsciiPiecePrintStrategy INSTANCE = new AsciiPiecePrintStrategy();

    @Override
    public void print(IPiece piece, PrintStream out) {
        if (piece == IPiece.NULL)
            out.print(' ');
        else if (piece.getColor() == Player.Color.BLACK)
            out.print(Character.toUpperCase(piece.getType().getAscii()));
        else if (piece.getColor() == Player.Color.WHITE)
            out.print(Character.toLowerCase(piece.getType().getAscii()));
        else
            out.print(' ');
    }

}
