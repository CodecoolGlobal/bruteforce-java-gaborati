package com.codecool.bruteforce.passwords.generator;

import com.codecool.bruteforce.logger.Logger;
import com.codecool.bruteforce.passwords.model.AsciiTableRange;

import java.util.Random;

public class PasswordGeneratorImpl implements PasswordGenerator {
    private static final Random random = new Random();
    private final AsciiTableRange[] characterSets;

    public PasswordGeneratorImpl(AsciiTableRange... characterSets) {
        this.characterSets = characterSets;
    }

    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            AsciiTableRange characterSet = getRandomCharacterSet();
            char randomChar = getRandomCharacter(characterSet);
            password.append(randomChar);
        }
        return password.toString();
    }

    private AsciiTableRange getRandomCharacterSet() {
        return characterSets[random.nextInt(characterSets.length)];
    }

    private static char getRandomCharacter(AsciiTableRange characterSet) {
        int randomValidAscii = characterSet.start()
                + random.nextInt(characterSet.end() - characterSet.start() + 1);
        return (char) randomValidAscii;
    }
}