package ru.olegcherednik.alice.chess.player;

import ru.olegcherednik.alice.chess.GameContext;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public class HumanPlayer extends BasePlayer {

    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public String nextPly(GameContext context) {
        return context.scan().next().toLowerCase().trim();
    }

    @Override
    public String toString() {
        return "Player " + color.getTitle();
    }

}
