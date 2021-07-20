package ru.olegcherednik.alice.chess.piece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Set;
import java.util.function.BiFunction;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public interface Piece {

    Piece NULL = new NullPiece();

    Id getId();

    String getCellId();

    Player.Color getColor();

    /** Returns {@literal true} when current piece can be eat by some opponent's piece */
    boolean isUnderPressure(GameContext context);

    /** Retrieves available moves for the current piece. */
    Set<String> getNextMoveCellIds(GameContext context);

    /** Retrieves moves that potentially can eat opponent's pieces */
    default Set<String> getNextEatCellIds(GameContext context) {
        return getNextMoveCellIds(context);
    }

    void moveTo(String toCellId);

    default Piece.Type getType() {
        return getId().getType();
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    enum Type {
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

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    enum Id {
        ROOK_A(Piece.Type.ROOK, 0, Rook::new),
        KNIGHT_B(Piece.Type.KNIGHT, 1, Knight::new),
        BISHOP_C(Piece.Type.BISHOP, 2, Bishop::new),
        QUEEN_D(Piece.Type.QUEEN, 3, Queen::new),
        KING_E(Piece.Type.KING, 4, King::new),
        BISHOP_F(Piece.Type.BISHOP, 5, Bishop::new),
        KNIGHT_G(Piece.Type.KNIGHT, 6, Knight::new),
        ROOK_H(Piece.Type.ROOK, 7, Rook::new),
        PAWN_A(Piece.Type.PAWN, 0, Pawn::new),
        PAWN_B(Piece.Type.PAWN, 1, Pawn::new),
        PAWN_C(Piece.Type.PAWN, 2, Pawn::new),
        PAWN_D(Piece.Type.PAWN, 3, Pawn::new),
        PAWN_E(Piece.Type.PAWN, 4, Pawn::new),
        PAWN_F(Piece.Type.PAWN, 5, Pawn::new),
        PAWN_G(Piece.Type.PAWN, 6, Pawn::new),
        PAWN_H(Piece.Type.PAWN, 7, Pawn::new);

        private final Piece.Type type;
        private final int initCol;
        private final BiFunction<Id, Player.Color, Piece> createPiece;

        public final Piece create(Player.Color color) {
            return createPiece.apply(this, color);
        }
    }

}
