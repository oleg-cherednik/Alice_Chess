package ru.olegcherednik.alice.chess.processor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.player.Player;

/**
 * Ply is the one move of any player, i.e. a half of the move. One move equals to two plies.<br>
 * <tt>strPly</tt> is the string representation of the {@code Ply} and has format like <tt>d2d4</tt>, which means the a piece goes from the cell
 * <tt>d2</tt> to the cell <tt>d4</tt>.
 *
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class Ply {

    private final int moveNo;
    private final Player.Color player;
    private final String fromCellId;
    private final String toCellId;

    public static Ply createFromStr(String strPly, int moveNo, Player.Color player) {
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
