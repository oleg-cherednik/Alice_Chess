package ru.olegcherednik.alice.chess;

import lombok.Getter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.exceptions.NotImplementedException;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.processor.Processor;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.ascii.AsciiBoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.unicode.UnicodeBoardPrintStrategy;

import java.io.PrintStream;
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
    private final Board board;
    private final Processor processor;

    public Game(InitialContext context) {
        out = context.getOut();
        err = context.getErr();
        boardPrintStrategy = context.getBoardPrintStrategy();
        scan = new Scanner(context.getIn());
        Player playerWhite = context.createWhitePlayer();
        Player playerBlack = context.createBlackPlayer();
        board = new Board(playerBlack, playerWhite);
        processor = new Processor(playerWhite, playerBlack, playerWhite);
    }

    public void start() throws InterruptedException {
        out.println("LET'S PLAY CHESS!!!");
        out.println("Player 1 (White) vs Player 2 (Black)");
        out.println("(move d2d4 -> move piece from cell 'd2' to 'd4')");
        out.println();

        while (true) {
            try {
                print();
                if (processor.doNextPlyAndFinishGame(this))
                    break;
            } catch(NotImplementedException e) {
                err.println(e.getMessage());
                break;
            } catch(ChessException e) {
                err.println(e.getMessage());
            } finally {
                Thread.sleep(200);
            }
        }

        print();
        out.println("Winner: " + processor.getCurrentPlayer());
        out.println("GAME OVER.");
    }

    private void print() {
        boardPrintStrategy.print(board, out);
        out.println();
    }

    @Override
    public Scanner scan() {
        return scan;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Player getCurrentPlayer() {
        return processor.getCurrentPlayer();
    }

    @Override
    public Player getOpponentPlayer() {
        return processor.getOpponentPlayer();
    }

    @Override
    public boolean isCheckKing() {
        return processor.isCheckKing();
    }

    private static final String MENU_OPTION_UTF8 = "utf8";
    private static final String MENU_OPTION_HELP = "h";

    public static void main(String... args) throws Exception {
        Options options = new Options();
        options.addOption(MENU_OPTION_UTF8, "unicode", false, "Use 'utf8' symbols");
        options.addOption(MENU_OPTION_HELP, "help", false, "Print this help");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption(MENU_OPTION_HELP))
            printHelp(options);
        else {
            InitialContext.InitialContextBuilder builder = InitialContext.builder();
            builder.boardPrintStrategy(cmd.hasOption(MENU_OPTION_UTF8) ? UnicodeBoardPrintStrategy.INSTANCE : AsciiBoardPrintStrategy.INSTANCE);
            builder.playerWhiteType(Player.Type.HUMAN);
            builder.playerBlackType(Player.Type.HUMAN);

            new Game(builder.build()).start();
        }
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("alice-chess.jar", options);
    }

}
