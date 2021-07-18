package ru.olegcherednik.alice.chess;

import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.unicode.UnicodeBoardPrintStrategy;

import java.io.UnsupportedEncodingException;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public class Main {

    public static void main(String... args) throws UnsupportedEncodingException {
        Game.Context context = Game.Context.builder()
//                                           .boardPrintStrategy(AsciiBoardPrintStrategy.INSTANCE)
                                           .boardPrintStrategy(UnicodeBoardPrintStrategy.INSTANCE)
                                           .playerWhiteType(Player.Type.HUMAN)
                                           .playerBlackType(Player.Type.HUMAN)
                                           .in(System.in)
                                           .out(System.out)
                                           .build();
        Game game = new Game(context);
        game.start();
    }

}
