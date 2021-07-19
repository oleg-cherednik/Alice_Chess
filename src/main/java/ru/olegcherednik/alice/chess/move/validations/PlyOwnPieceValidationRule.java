package ru.olegcherednik.alice.chess.move.validations;

import ru.olegcherednik.alice.chess.GameContext;
import ru.olegcherednik.alice.chess.piece.Piece;
import ru.olegcherednik.alice.chess.exceptions.ChessException;
import ru.olegcherednik.alice.chess.move.Ply;
import ru.olegcherednik.alice.chess.player.Player;

/**
 * @author Oleg Cherednik
 * @since 19.07.2021
 */
final class PlyOwnPieceValidationRule implements ValidationRule {

    @Override
    public void validate(String strPly, GameContext context) {
        String fromCellId = Ply.getFromCellId(strPly);
        Piece piece = context.cell(fromCellId).getPiece();
        Player.Color player = context.currentPlayer();

        if (piece.getColor() != player)
            throw new ChessException(String.format("Piece on cell '%s' does not belong to player '%s'",
                    fromCellId, player.getTitle()));
    }

}
