package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.olegcherednik.alice.chess.move.Processor;
import ru.olegcherednik.alice.chess.player.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class implements one piece on the chess board.
 *
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
@Getter
@RequiredArgsConstructor
public final class Piece {

    public static final Piece NULL = new Piece(null, null);

    private final Id id;
    private final Player.Color color;

    public Piece.Type getType() {
        return id.type;
    }

    public Set<String> getAvailableMoves(String fromCellId, GameContext context) {
        return getType().getAvailableMoves(fromCellId, context);
    }

    @Override
    public String toString() {
        return id + "_" + color;
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Type {
        ROOK("Rook", 'r', "♖", "♜"),
        KNIGHT("Knight", 'n', "♘", "♞"),
        BISHOP("Bishop", 'b', "♗", "♝"),
        QUEEN("Queen", 'q', "♕", "♛"),
        KING("King", 'k', "♔", "♚"),
        PAWN("Pawn", 'p', "♙", "♟") {
            @Override
            public Set<String> getAvailableMoves(String fromCellId, GameContext context) {
                Player.Color player = context.player();
                int col = Processor.getCellCol(fromCellId);
                int row = Processor.getCellRow(fromCellId);
                Set<String> cellIds = new HashSet<>();

                if (player == Player.Color.WHITE) {
                    addWithinBoardCell(cellIds, col, row - 1, context);
                    addWithinBoardCell(cellIds, col, row - 2, context);
                } else if (player == Player.Color.BLACK) {
                    addWithinBoardCell(cellIds, col, row + 1, context);
                    addWithinBoardCell(cellIds, col, row + 2, context);
                } else
                    return Collections.emptySet();

                return cellIds;
            }
        };

        private final String title;
        private final char ascii;
        private final String unicodeWhite;
        private final String unicodeBlack;

        // Some of these cells could be taken or be out of the border
        public Set<String> getAvailableMoves(String fromCellId, GameContext context) {
            return Collections.emptySet();
        }

        protected static void addWithinBoardCell(Set<String> cellIds, int col, int row, GameContext context) {
            String toCellId = Processor.getCellId(col, row);

            if (context.cell(toCellId) != Board.Cell.NULL)
                cellIds.add(toCellId);
        }

    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Id {
        ROOK_A(Type.ROOK, 0),
        KNIGHT_B(Type.KNIGHT, 1),
        BISHOP_C(Type.BISHOP, 2),
        QUEEN_D(Type.QUEEN, 3),
        KING_E(Type.KING, 4),
        BISHOP_F(Type.BISHOP, 5),
        KNIGHT_G(Type.KNIGHT, 6),
        ROOK_H(Type.ROOK, 7),
        PAWN_A(Type.PAWN, 0),
        PAWN_B(Type.PAWN, 1),
        PAWN_C(Type.PAWN, 2),
        PAWN_D(Type.PAWN, 3),
        PAWN_E(Type.PAWN, 4),
        PAWN_F(Type.PAWN, 5),
        PAWN_G(Type.PAWN, 6),
        PAWN_H(Type.PAWN, 7);

        private final Type type;
        private final int col;

        public Piece create(Player.Color color) {
            return new Piece(this, color);
        }
    }

}
