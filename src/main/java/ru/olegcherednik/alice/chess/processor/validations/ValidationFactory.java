package ru.olegcherednik.alice.chess.processor.validations;

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

    // order is important
    public static List<ValidationRule> createValidationRules() {
        List<ValidationRule> rules = new ArrayList<>();
        rules.add(new IncorrectInputValidationRule());
        rules.add(new CellWithinBoardValidationRule());
        rules.add(new CellEmptyValidationRule());
        rules.add(new EatOwnPieceValidationRule());
        rules.add(new PlyOwnPieceValidationRule());
        rules.add(new IncorrectPieceMoveValidationRule());
        return Collections.unmodifiableList(rules);
    }

}
