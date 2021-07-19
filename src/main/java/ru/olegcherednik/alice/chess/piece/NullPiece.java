package ru.olegcherednik.alice.chess.piece;

/**
 * @author Oleg Cherednik
 * @since 20.07.2021
 */
final class NullPiece extends AbstractPiece {

    NullPiece() {
        super(null, null);
    }

    @Override
    public String toString() {
        return "<null>";
    }

}
