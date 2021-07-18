package ru.olegcherednik.alice.chess;

import lombok.Builder;
import lombok.NonNull;
import lombok.Setter;
import ru.olegcherednik.alice.chess.player.Player;
import ru.olegcherednik.alice.chess.visualization.BoardPrintStrategy;
import ru.olegcherednik.alice.chess.visualization.ascii.AsciiBoardPrintStrategy;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public final class Game {

    private final Context context;
    private final Player playerWhite;
    private final Player playerBlack;
    private final Board board;
    private final Play play;
    private final Scanner scan;

    public Game(Context context) {
        this.context = context;
        playerWhite = context.createWhitePlayer();
        playerBlack = context.createBlackPlayer();
        board = new Board(playerBlack, playerWhite);
        scan = new Scanner(context.in);
        play = new Play(scan, context.out, playerWhite);
    }

    public void start() {
        context.out.println("LET'S PLAY CHESS!!!");
        context.out.println("Player White vs Player Black");
        context.out.println();

        print();

        int moveNo = 1;

        while (true) {
            context.out.println();
            context.out.format("Move %d (%s) > ", moveNo++, play.nextMovePlayer);
            String move = scan.nextLine();
            Board.Cell cellFrom = board.getCell(move.substring(0, 2));
            Board.Cell cellTo = board.getCell(move.substring(3));

            cellTo.setPiece(cellFrom.getPiece());
            cellFrom.clear();

            play.setNextMovePlayer(playerWhite == play.nextMovePlayer ? playerBlack : playerWhite);

            print();
        }
    }

    private void print() {
        context.boardPrintStrategy.print(board, context.out);
    }

    public static final class Play {

        private final Scanner scan;
        private final PrintStream out;
        @Setter
        private Player nextMovePlayer;

        public Play(Scanner scan, PrintStream out, Player nextMovePlayer) {
            this.scan = scan;
            this.out = out;
            this.nextMovePlayer = nextMovePlayer;
        }

    }

    @Builder
    public static final class Context {

        @NonNull
        @Builder.Default
        private final BoardPrintStrategy boardPrintStrategy = AsciiBoardPrintStrategy.INSTANCE;
        @NonNull
        @Builder.Default
        private final Player.Type playerWhiteType = Player.Type.HUMAN;
        @NonNull
        @Builder.Default
        private final Player.Type playerBlackType = Player.Type.HUMAN;
        @NonNull
        @Builder.Default
        private final InputStream in = System.in;
        @NonNull
        @Builder.Default
        private final PrintStream out = System.out;

        public Player createWhitePlayer() {
            return playerWhiteType.create(Player.Color.WHITE);
        }

        public Player createBlackPlayer() {
            return playerBlackType.create(Player.Color.BLACK);
        }

    }

}
