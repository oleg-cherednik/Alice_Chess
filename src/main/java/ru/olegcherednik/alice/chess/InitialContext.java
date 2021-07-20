package ru.olegcherednik.alice.chess;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.ascii.AsciiBoardPrintStrategy;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author Oleg Cherednik
 * @since 20.07.2021
 */
@Getter
@Builder
public final class InitialContext {

    @NonNull
    @Builder.Default
    private final BoardPrintStrategy boardPrintStrategy = AsciiBoardPrintStrategy.INSTANCE;
    @NonNull
    @Builder.Default
    private final Player.Type playerWhiteType = Player.Type.HUMAN;
    @NonNull
    @Builder.Default
    private final Player.Type playerBlackType = Player.Type.HUMAN;
    @NonNull
    @Builder.Default
    private final InputStream in = System.in;
    @NonNull
    @Builder.Default
    private final PrintStream out = System.out;
    @NonNull
    @Builder.Default
    private final PrintStream err = System.err;

    public Player createWhitePlayer() {
        return playerWhiteType.create(Player.Color.WHITE);
    }

    public Player createBlackPlayer() {
        return playerBlackType.create(Player.Color.BLACK);
    }

}
