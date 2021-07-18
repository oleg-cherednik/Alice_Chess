package ru.olegcherednik.alice.chess.player;

import ru.olegcherednik.alice.chess.Piece;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
abstract class AbstractPlayer implements Player {

    protected final Color color;
    protected final Map<Piece.Id, Piece> pieces;

    protected AbstractPlayer(Color color) {
        this.color = color;
        pieces = createPieces(color);
    }

    @Override
    public final Piece getPiece(Piece.Id id) {
        return pieces.getOrDefault(id, Piece.NULL);
    }

    private static Map<Piece.Id, Piece> createPieces(Color color) {
        Map<Piece.Id, Piece> pieces = new EnumMap<>(Piece.Id.class);

        for (Piece.Id id : Piece.Id.values())
            pieces.put(id, id.create(color));

        return pieces;
    }

}
