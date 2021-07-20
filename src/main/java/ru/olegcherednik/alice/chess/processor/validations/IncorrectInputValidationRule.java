package ru.olegcherednik.alice.chess.processor.validations;

import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.ChessException;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class IncorrectInputValidationRule implements ValidationRule {

    @Override
    public void validate(String strPly, GameContext context) {
        boolean valid = strPly.length() == 4;
        valid = valid && Character.isLowerCase(strPly.charAt(0)) && Character.isLetter(strPly.charAt(0));
        valid = valid && Character.isDigit(strPly.charAt(1));
        valid = valid && Character.isLowerCase(strPly.charAt(2)) && Character.isLetter(strPly.charAt(2));
        valid = valid && Character.isDigit(strPly.charAt(3));

        if (!valid)
            throw new ChessException(String.format("'%s' format is not correct (expected i.e. d2d4)", strPly));
    }

}
