package ru.olegcherednik.alice.chess;

import ru.olegcherednik.alice.chess.player.Player;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public interface GameContext {

    PrintStream out();

    Scanner scan();

    Board.Cell cell(String cellId);

    Player.Color player();

}
