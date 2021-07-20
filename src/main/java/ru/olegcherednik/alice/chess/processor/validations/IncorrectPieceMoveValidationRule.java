package ru.olegcherednik.alice.chess.processor.validations;

import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.piece.Piece;
import ru.olegcherednik.alice.chess.processor.Ply;

import java.util.Set;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class IncorrectPieceMoveValidationRule implements ValidationRule {

    @Override
    public void validate(String strPly, GameContext context) {
        String fromCellId = Ply.getFromCellId(strPly);
        String toCellId = Ply.getToCellId(strPly);
        Piece piece = context.getBoard().getCell(fromCellId).getPiece();
        Set<String> availableToCellIds = piece.getNextMoveCellIds(context);

        if (!availableToCellIds.contains(toCellId))
            throw new ChessException(String.format("Piece '%s' on cell '%s' cannot move to the cell '%s'",
                    piece.getType().getTitle(), fromCellId, toCellId));
    }

}
