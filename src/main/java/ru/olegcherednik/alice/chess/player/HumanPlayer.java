package ru.olegcherednik.alice.chess.player;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return "Player " + color.getTitle();
    }
}
