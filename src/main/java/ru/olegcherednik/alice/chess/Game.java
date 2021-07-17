package ru.olegcherednik.alice.chess;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 * @author Oleg Cherednik
 * @since 18.07.2021
 */
public final class Game {

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Team {
        BLACK(Character::toUpperCase),
        WHITE(Character::toLowerCase);

        private final Function<Character, Character> convertPrintableChar;

        public final char apply(char ch) {
            return convertPrintableChar.apply(ch);
        }
    }

    public static final class Context {

    }
}
