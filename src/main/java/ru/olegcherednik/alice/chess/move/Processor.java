package ru.olegcherednik.alice.chess.move;

import lombok.Getter;
import ru.olegcherednik.alice.chess.Board;
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
    private Player currentPlayer;
    @Getter
    private int moveNo;

    public Processor(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Ply doNextPly(GameContext context) {
        context.getOut().format("Move %d (%s) > ", moveNo + 1, currentPlayer.getColor().getTitle());

        String strPly = Board.Cell.normalizeStrPly(currentPlayer.nextPly(context));
        validationProcessor.validate(strPly, context);

        Ply ply = Ply.createFromStr(strPly, moveNo, currentPlayer.getColor());
        plies.add(ply);

        return ply;
    }

    public void switchToPlayer(Player player) {
        currentPlayer = player;

        if (player.getColor() == Player.Color.WHITE)
            moveNo++;
    }

}
