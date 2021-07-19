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
    ROOK_A(PieceType.ROOK, 0, Rook::new),
    KNIGHT_B(PieceType.KNIGHT, 1, Knight::new),
    BISHOP_C(PieceType.BISHOP, 2, Bishop::new),
    QUEEN_D(PieceType.QUEEN, 3, Queen::new),
    KING_E(PieceType.KING, 4, King::new),
    BISHOP_F(PieceType.BISHOP, 5, Bishop::new),
    KNIGHT_G(PieceType.KNIGHT, 6, Knight::new),
    ROOK_H(PieceType.ROOK, 7, Rook::new),
    PAWN_A(PieceType.PAWN, 0, Pawn::new),
    PAWN_B(PieceType.PAWN, 1, Pawn::new),
    PAWN_C(PieceType.PAWN, 2, Pawn::new),
    PAWN_D(PieceType.PAWN, 3, Pawn::new),
    PAWN_E(PieceType.PAWN, 4, Pawn::new),
    PAWN_F(PieceType.PAWN, 5, Pawn::new),
    PAWN_G(PieceType.PAWN, 6, Pawn::new),
    PAWN_H(PieceType.PAWN, 7, Pawn::new);

    private final PieceType type;
    private final int initCol;
    private final BiFunction<PieceId, Player.Color, IPiece> createPiece;

    public final IPiece create(Player.Color color) {
        return createPiece.apply(this, color);
    }
}
