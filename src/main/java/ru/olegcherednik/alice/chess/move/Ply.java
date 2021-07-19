package ru.olegcherednik.alice.chess.move;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.player.Player;

/**
 * Ply is the one move of any player, i.e. a half of the move. One move equals to two plies.
 *
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
@Getter
@RequiredArgsConstructor
public final class Ply {

    private final int moveNo;
    private final Player.Color player;
    private final String fromCellId;
    private final String toCellId;

    public static Ply createFromStr(String strPly, int moveNo, Player.Color player) {

        // TODO validation

        String fromCellId = getFromCellId(strPly);
        String toCellId = getToCellId(strPly);
        return new Ply(moveNo, player, fromCellId, toCellId);
    }

    public static String getFromCellId(String strPly) {
        return strPly.substring(0, 2);
    }

    public static String getToCellId(String strPly) {
        return strPly.substring(2, 4);
    }

    @Override
    public String toString() {
        return fromCellId + toCellId;
    }

}
