package ru.olegcherednik.alice.chess.player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.piece.Piece;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public interface Player {

    Piece getPiece(Piece.PieceId id);

    Collection<Piece> getPieces();

    Color getColor();

    String nextPly(GameContext context);

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
        WHITE("White");

        private final String title;

        public final Color getOpponent() {
            return this == BLACK ? WHITE : BLACK;
        }
    }

}
