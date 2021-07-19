package ru.olegcherednik.alice.chess.piece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Oleg Cherednik
 * @since 20.07.2021
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum PieceType {
    ROOK("Rook", 'r', "♖", "♜"),
    KNIGHT("Knight", 'n', "♘", "♞"),
    BISHOP("Bishop", 'b', "♗", "♝"),
    QUEEN("Queen", 'q', "♕", "♛"),
    KING("King", 'k', "♔", "♚"),
    PAWN("Pawn", 'p', "♙", "♟");

    private final String title;
    private final char ascii;
    private final String unicodeWhite;
    private final String unicodeBlack;

}
