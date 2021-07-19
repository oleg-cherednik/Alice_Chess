package ru.olegcherednik.alice.chess.move.validations;

import ru.olegcherednik.alice.chess.GameContext;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
interface ValidationRule {

    void validate(String strPly, GameContext context);

}
