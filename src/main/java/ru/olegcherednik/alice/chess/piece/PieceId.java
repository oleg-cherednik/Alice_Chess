package ru.olegcherednik.alice.chess.piece;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.function.BiFunction;

/**
 * @author Oleg Cherednik
 * @since 20.07.2021
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum PieceId {
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
    private final BiFunction<PieceId, Player.Color, Piece> createPiece;

    public final Piece create(Player.Color color) {
        return createPiece.apply(this, color);
    }
}
