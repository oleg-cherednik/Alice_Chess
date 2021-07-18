package ru.olegcherednik.alice.chess;

import lombok.Builder;
import lombok.NonNull;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.ascii.AsciiBoardPrintStrategy;

import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public final class Game {

    private final Context context;
    private final Player playerWhite;
    private final Player playerBlack;
    private final Board board;

    public Game(Context context) {
        this.context = context;
        playerWhite = context.createWhitePlayer();
        playerBlack = context.createBlackPlayer();
        board = new Board(playerBlack, playerWhite);
    }

    public void print(PrintStream out) {
        context.boardPrintStrategy.print(board, out);
    }

    @Builder
    public static final class Context {

        @NonNull
        @Builder.Default
        private final BoardPrintStrategy boardPrintStrategy = AsciiBoardPrintStrategy.INSTANCE;
        @NonNull
        @Builder.Default
        private final Player.Type playerWhiteType = Player.Type.HUMAN;
        @NonNull
        @Builder.Default
        private final Player.Type playerBlackType = Player.Type.HUMAN;

        public Player createWhitePlayer() {
            return playerWhiteType.create(Player.Color.WHITE);
        }

        public Player createBlackPlayer() {
            return playerBlackType.create(Player.Color.BLACK);
        }

    }
}
