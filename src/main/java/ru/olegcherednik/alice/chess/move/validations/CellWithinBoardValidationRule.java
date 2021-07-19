package ru.olegcherednik.alice.chess.move.validations;

import ru.olegcherednik.alice.chess.Board;
import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.move.Ply;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class CellWithinBoardValidationRule implements ValidationRule {

    @Override
    public void validate(String strPly, GameContext context) {
        String fromCellId = Ply.getFromCellId(strPly);
        String toCellId = Ply.getToCellId(strPly);

        if (context.getBoard().getCell(fromCellId) == Board.Cell.NULL)
            throw new ChessException(String.format("Cell '%s' is out of board", fromCellId));
        if (context.getBoard().getCell(toCellId) == Board.Cell.NULL)
            throw new ChessException(String.format("Cell '%s' is out of board", toCellId));
    }

}
