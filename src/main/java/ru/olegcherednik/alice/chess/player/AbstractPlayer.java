package ru.olegcherednik.alice.chess.player;

import lombok.Getter;
import ru.olegcherednik.alice.chess.piece.Piece;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
abstract class AbstractPlayer implements Player {

    @Getter
    protected final Color color;
    protected final Map<Piece.Id, Piece> pieces;

    protected AbstractPlayer(Color color) {
        this.color = color;
        pieces = createPieces(color);
    }

    @Override
    public Piece getPiece(Piece.Id id) {
        return pieces.getOrDefault(id, Piece.NULL);
    }

    @Override
    public Collection<Piece> getPieces() {
        return pieces.isEmpty() ? List.of() : Collections.unmodifiableCollection(pieces.values());
    }

    @Override
    public boolean removePiece(Piece.Id pieceId) {
        return pieces.remove(pieceId) != null;
    }

    @Override
    public String toString() {
        return color.getTitle();
    }

    private static Map<Piece.Id, Piece> createPieces(Color color) {
        return Arrays.stream(Piece.Id.values())
                     .map(pieceId -> pieceId.create(color))
                     .collect(Collectors.toMap(Piece::getId, Function.identity()));
    }

}
