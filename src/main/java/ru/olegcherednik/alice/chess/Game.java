package ru.olegcherednik.alice.chess;

import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.move.Ply;
import ru.olegcherednik.alice.chess.move.Processor;
import ru.olegcherednik.alice.chess.piece.Piece;
import ru.olegcherednik.alice.chess.player.Player;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public final class Game implements GameContext {

    private final InitialContext context;
    private final Scanner scan;
    private final Player playerWhite;
    private final Player playerBlack;
    private final Board board;
    private final Processor processor;

    public Game(InitialContext context) {
        this.context = context;
        scan = new Scanner(context.getIn());
        playerWhite = context.createWhitePlayer();
        playerBlack = context.createBlackPlayer();
        board = new Board(playerBlack, playerWhite);
        processor = new Processor(playerWhite);
    }

    public void start() throws InterruptedException {
        context.getOut().println("LET'S PLAY CHESS!!!");
        context.getOut().println("Player 1 (White) vs Player 2 (Black)");
        context.getOut().println();

        while (true) {
            try {
                print();

                Ply ply = processor.doNextPly(this);
                board.movePiece(ply.getFromCellId(), ply.getToCellId());
                updateCurrentPlayerCellProtection();
                processor.switchToPlayer(playerWhite == processor.getCurrentPlayer() ? playerBlack : playerWhite);
            } catch (ChessException e) {
                context.getErr().println(e.getMessage());
                Thread.sleep(200);
            }
        }
    }

    private void updateCurrentPlayerCellProtection() {
        Player currentPlayer = processor.getCurrentPlayer();

        for (Piece piece : currentPlayer.getPieces()) {
            for (String cellId : piece.getNextEatCellIds(this)) {
                int a = 0;
                a++;
            }
        }
    }

    private void print() {
        context.getBoardPrintStrategy().print(board, context.getOut());
        context.getOut().println();
    }

    @Override
    public PrintStream out() {
        return context.getOut();
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
    public Player.Color getCurrentPlayer() {
        return processor.getCurrentPlayer().getColor();
    }

}
