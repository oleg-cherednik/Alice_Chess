package ru.olegcherednik.alice.chess.move.validations;

import ru.olegcherednik.alice.chess.GameContext;

import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
public final class ValidationProcessor {

    private final List<ValidationRule> validationRules = ValidationFactory.createValidationRules();

    public void validate(String strPly, GameContext context) {
        for (ValidationRule rule : validationRules)
            rule.validate(strPly, context);
    }

}
