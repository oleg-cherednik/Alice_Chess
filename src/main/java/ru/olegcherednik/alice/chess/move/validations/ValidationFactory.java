package ru.olegcherednik.alice.chess.move.validations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationFactory {

    public static List<ValidationRule> createValidationRules() {
        List<ValidationRule> validationRules = new ArrayList<>();
        validationRules.add(new IncorrectInputValidationRule());
        validationRules.add(new OutOfBoardValidationRule());
        validationRules.add(new CellIsEmptyValidationRule());
        return Collections.unmodifiableList(validationRules);
    }

}
