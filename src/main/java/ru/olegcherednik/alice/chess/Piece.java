package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class implements one piece on the chess board.
 *
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@Getter
public final class Piece {

    private static final AtomicInteger ID = new AtomicInteger(0);
    public static final Piece EMPTY = new Piece(null, null);

    private final int id;
    private final Type type;
    private final Player.Color color;

    public Piece(Type type, Player.Color color) {
        id = ID.getAndIncrement();
        this.type = type;
        this.color = color;
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {
        ROOK('r', "\u2656", "\u265C"),
        KNIGHT('n', "\u2658", "\u265E"),
        BISHOP('b', "\u2657", "\u265D"),
        QUEEN('q', "\u2655", "\u265B"),
        KING('k', "\u2654", "\u265A"),
        PAWN('p', "\u2659", "\u265F");

        private final char ascii;
        private final String unicodeWhite;
        private final String unicodeBlack;

    }
}
