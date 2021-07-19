package ru.olegcherednik.alice.chess.move;

import lombok.Getter;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.move.validations.ValidationProcessor;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public final class Processor {

    private final List<Ply> plies = new ArrayList<>();
    private final ValidationProcessor validationProcessor = new ValidationProcessor();
    @Getter
    private Player player;
    @Getter
    private int moveNo;

    public Processor(Player player) {
        this.player = player;
    }

    public Ply doNextPly(GameContext context) {
        context.out().format("Move %d (%s) > ", moveNo + 1, player.getColor().getTitle());

        String strPly = normalizeStrPly(player.nextPly(context));
        validationProcessor.validate(strPly, context);

        Ply ply = Ply.createFromStr(strPly, moveNo, player.getColor());
        plies.add(ply);

        return ply;
    }

    public void switchToPlayer(Player player) {
        this.player = player;

        if (player.getColor() == Player.Color.WHITE)
            moveNo++;
    }

    public static String normalizeStrPly(String strPly) {
        return strPly.toLowerCase().trim();
    }

    public static int getCellRow(String cellId) {
        return '8' - cellId.charAt(1);
    }

    public static int getCellCol(String cellId) {
        return cellId.charAt(0) - 'a';
    }

    public static String getCellId(int col, int row) {
        return (char)('a' + col) + String.valueOf(8 - row);
    }

}
