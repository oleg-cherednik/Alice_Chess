package ru.olegcherednik.alice.chess.move.validations;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.ChessException;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class OutOfBoardValidationRule implements ValidationRule {

    @Override
    public void validate(String strPly, GameContext context) {
        boolean valid = isCorrectCol(strPly.charAt(0)) && isCorrectCol(strPly.charAt(2));
        valid &= isCorrectRow(strPly.charAt(1)) && isCorrectRow(strPly.charAt(3));

        if (!valid)
            throw new ChessException(String.format("'%s' is out of board", strPly));
    }

    private static boolean isCorrectCol(char ch) {
        return ch >= 'a' && ch <= 'a' + Board.HEIGHT;
    }

    private static boolean isCorrectRow(char ch) {
        return ch >= '1' && ch <= '1' + Board.WIDTH;
    }

}
