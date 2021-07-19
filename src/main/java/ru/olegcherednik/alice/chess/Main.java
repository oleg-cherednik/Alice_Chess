package ru.olegcherednik.alice.chess;

import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.unicode.UnicodeBoardPrintStrategy;

import java.io.UnsupportedEncodingException;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public class Main {

    public static void main(String... args) throws UnsupportedEncodingException, InterruptedException {
        Game.InitialContext context = Game.InitialContext.builder()
//                                           .boardPrintStrategy(AsciiBoardPrintStrategy.INSTANCE)
                                                         .boardPrintStrategy(UnicodeBoardPrintStrategy.INSTANCE)
                                                         .playerWhiteType(Player.Type.HUMAN)
                                                         .playerBlackType(Player.Type.HUMAN)
                                                         .build();
        Game game = new Game(context);
        game.start();
    }

}
