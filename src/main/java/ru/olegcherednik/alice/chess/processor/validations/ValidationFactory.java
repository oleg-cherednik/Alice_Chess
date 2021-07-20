package ru.olegcherednik.alice.chess.processor.validations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationFactory {

    /** rule order is important */
    public static List<ValidationRule> createValidationRules() {
        return List.of(
                new IncorrectInputValidationRule(),
                new CellWithinBoardValidationRule(),
                new CellEmptyValidationRule(),
                new EatOwnPieceValidationRule(),
                new PlyOwnPieceValidationRule(),
                new IncorrectPieceMoveValidationRule());
        // TODO add rule if check to a king, then next move should avoid it
    }

}
