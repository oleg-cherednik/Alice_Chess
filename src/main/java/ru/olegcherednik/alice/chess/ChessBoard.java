package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Oleg Cherednik
 * @since 16.07.2021
 */
public class ChessBoard {


    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Row {
        ROW_A('A'),
        ROW_B('B'),
        ROW_C('C'),
        ROW_D('D'),
        ROW_E('E'),
        ROW_F('F'),
        ROW_G('G'),
        ROW_H('H');

        @Getter
        private final char id;
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Column {
        COL_1('1'),
        COL_2('2'),
        COL_3('3'),
        COL_4('4'),
        COL_5('5'),
        COL_6('6'),
        COL_7('7'),
        COL_8('8');

        @Getter
        private final char id;
    }


}
