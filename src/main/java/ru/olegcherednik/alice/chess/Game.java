package ru.olegcherednik.alice.chess;

import lombok.Getter;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.exceptions.NotImplementedException;
import ru.olegcherednik.alice.chess.move.Ply;
import ru.olegcherednik.alice.chess.move.Processor;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.unicode.UnicodeBoardPrintStrategy;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public final class Game implements GameContext {

    @Getter
    private final PrintStream out;
    private final PrintStream err;
    private final BoardPrintStrategy boardPrintStrategy;
    private final Scanner scan;
    private final Player playerWhite;
    private final Player playerBlack;
    private final Board board;
    private final Processor processor;

    public Game(InitialContext context) {
        out = context.getOut();
        err = context.getErr();
        boardPrintStrategy = context.getBoardPrintStrategy();
        scan = new Scanner(context.getIn());
        playerWhite = context.createWhitePlayer();
        playerBlack = context.createBlackPlayer();
        board = new Board(playerBlack, playerWhite);
        processor = new Processor(playerWhite);
    }

    public void start() throws InterruptedException {
        out.println("LET'S PLAY CHESS!!!");
        out.println("Player 1 (White) vs Player 2 (Black)");
        out.println();

        while (true) {
            try {
                print();

                Ply ply = processor.doNextPly(this);
                board.movePiece(ply.getFromCellId(), ply.getToCellId());
                processor.updateCurrentPlayerCellProtection(this);
                processor.switchToPlayer(playerWhite == processor.getCurrentPlayer() ? playerBlack : playerWhite);
            } catch (NotImplementedException e) {
                err.println(e.getMessage());
                break;
            } catch (ChessException e) {
                err.println(e.getMessage());
                Thread.sleep(200);
            }
        }
    }

    private void print() {
        boardPrintStrategy.print(board, out);
        out.println();
    }

    // ========== GameContext ==========

    @Override
    public Scanner scan() {
        return scan;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Player.Color getCurrentPlayer() {
        return processor.getCurrentPlayer().getColor();
    }

    // ========== Main ==========

    public static void main(String... args) throws UnsupportedEncodingException, InterruptedException {
        InitialContext context = InitialContext.builder()
//                                           .boardPrintStrategy(AsciiBoardPrintStrategy.INSTANCE)
                                               .boardPrintStrategy(UnicodeBoardPrintStrategy.INSTANCE)
                                               .playerWhiteType(Player.Type.HUMAN)
                                               .playerBlackType(Player.Type.HUMAN)
                                               .build();
        new Game(context).start();
    }

}
