package ru.olegcherednik.alice.chess;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public final class Game {

    private final Context context;
    private final Board board = new Board();

    public Game(Context context) {
        this.context = context;
    }

    public void print(PrintStream out) {
        context.getBoardPrintStrategy().print(board, out);
    }

    public enum Team {
        BLACK,
        WHITE;
    }

    @Getter
    @Builder
    public static final class Context {

        @NonNull
        private final BoardPrintStrategy boardPrintStrategy;
    }
}
