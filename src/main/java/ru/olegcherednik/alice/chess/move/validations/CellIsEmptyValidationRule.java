package ru.olegcherednik.alice.chess.move.validations;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.move.Ply;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class CellIsEmptyValidationRule implements ValidationRule {

    @Override
    public void validate(String strPly, GameContext context) {
        String cellId = Ply.getFromCellId(strPly);
        Board.Cell cell = context.cell(cellId);

        if (cell.isEmpty())
            throw new ChessException(String.format("cell '%s' is empty", cellId));
    }

}
