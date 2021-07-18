package ru.olegcherednik.alice.chess.player;

import ru.olegcherednik.alice.chess.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
abstract class AbstractPlayer implements Player {

    private final List<Piece> pieces;

    protected AbstractPlayer(Color color) {
        pieces = createPieces(color);
    }

    @Override
    public final List<Piece> getPieces(Piece.Type type) {
        return pieces.stream()
                     .filter(piece -> piece.getType() == type)
                     .collect(Collectors.toList());
    }

    private static List<Piece> createPieces(Color color) {
        List<Piece> pieces = new ArrayList<>();
        createPiece(pieces, Piece.Type.ROOK, color, 2);
        createPiece(pieces, Piece.Type.KNIGHT, color, 2);
        createPiece(pieces, Piece.Type.BISHOP, color, 2);
        createPiece(pieces, Piece.Type.QUEEN, color, 1);
        createPiece(pieces, Piece.Type.KING, color, 1);
        createPiece(pieces, Piece.Type.PAWN, color, 8);
        return pieces;
    }

    private static void createPiece(List<Piece> pieces, Piece.Type type, Color color, int total) {
        for (int i = 0; i < total; i++)
            pieces.add(new Piece(type, color));
    }
}
