package ru.olegcherednik.alice.chess.player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.Piece;

import java.util.function.Function;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public interface Player {

    Piece getPiece(Piece.Id id);

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    enum Type {
        HUMAN(HumanPlayer::new),
        BOT(BotPlayer::new);

        private final Function<Color, Player> createPlayer;

        public final Player create(Color color) {
            return createPlayer.apply(color);
        }
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    enum Color {
        BLACK("Black"),
        WHITE("Whites");

        private final String title;
    }
}
