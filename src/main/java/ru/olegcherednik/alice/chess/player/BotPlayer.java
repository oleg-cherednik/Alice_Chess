package ru.olegcherednik.alice.chess.player;

import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.NotImplementedException;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public class BotPlayer extends AbstractPlayer {

    public BotPlayer(Color color) {
        super(color);
        throw new NotImplementedException("BotPlayer");
    }

    @Override
    public String nextPly(GameContext context) {
        return null;
    }

}
