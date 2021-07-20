package ru.olegcherednik.alice.chess.piece;

import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class Queen extends AbstractPiece {

    public Queen(Id id, Player.Color color) {
        super(id, color);
    }

    @Override
    public Set<String> getNextMoveCellIds(GameContext context) {
        return Stream.of(
                getMultiMoves(-1, 0, context),
                getMultiMoves(1, 0, context),
                getMultiMoves(0, -1, context),
                getMultiMoves(0, 1, context),
                getMultiMoves(-1, -1, context),
                getMultiMoves(-1, 1, context),
                getMultiMoves(1, -1, context),
                getMultiMoves(1, 1, context))
                     .flatMap(Set::stream)
                     .collect(Collectors.toSet());
    }

}
