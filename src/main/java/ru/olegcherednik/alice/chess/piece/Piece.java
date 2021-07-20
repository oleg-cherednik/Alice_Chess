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
        ROOK("Rook", 'r', "♖", "♜", Rook::new),
        KNIGHT("Knight", 'n', "♘", "♞", Knight::new),
        BISHOP("Bishop", 'b', "♗", "♝", Bishop::new),
        QUEEN("Queen", 'q', "♕", "♛", Queen::new),
        KING("King", 'k', "♔", "♚", King::new),
        PAWN("Pawn", 'p', "♙", "♟", Pawn::new);

        private final String title;
        private final char ascii;
        private final String unicodeWhite;
        private final String unicodeBlack;
        @Getter(AccessLevel.NONE)
        private final BiFunction<Id, Player.Color, Piece> createPiece;

    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    enum Id {
        ROOK_A(Piece.Type.ROOK, 0),
        KNIGHT_B(Piece.Type.KNIGHT, 1),
        BISHOP_C(Piece.Type.BISHOP, 2),
        QUEEN_D(Piece.Type.QUEEN, 3),
        KING_E(Piece.Type.KING, 4),
        BISHOP_F(Piece.Type.BISHOP, 5),
        KNIGHT_G(Piece.Type.KNIGHT, 6),
        ROOK_H(Piece.Type.ROOK, 7),
        PAWN_A(Piece.Type.PAWN, 0),
        PAWN_B(Piece.Type.PAWN, 1),
        PAWN_C(Piece.Type.PAWN, 2),
        PAWN_D(Piece.Type.PAWN, 3),
        PAWN_E(Piece.Type.PAWN, 4),
        PAWN_F(Piece.Type.PAWN, 5),
        PAWN_G(Piece.Type.PAWN, 6),
        PAWN_H(Piece.Type.PAWN, 7);

        private final Piece.Type type;
        private final int initCol;

        public final Piece create(Player.Color color) {
            return type.createPiece.apply(this, color);
        }
    }

}
