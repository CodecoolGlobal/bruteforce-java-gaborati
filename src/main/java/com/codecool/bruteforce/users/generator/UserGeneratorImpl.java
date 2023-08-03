package com.codecool.bruteforce.users.generator;

import com.codecool.bruteforce.logger.Logger;
import com.codecool.bruteforce.passwords.generator.PasswordGenerator;
import com.codecool.bruteforce.users.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserGeneratorImpl implements UserGenerator {
    private Logger logger;
    private final List<PasswordGenerator> passwordGenerators;
    private static final Random random = new Random();
    private int userCount;

    public UserGeneratorImpl(Logger logger, List<PasswordGenerator> passwordGenerators) {
        this.logger = logger;
        this.passwordGenerators = passwordGenerators;
    }

    @Override
    public List<User> generate(int count, int maxPasswordLength) {
        List<User> users = new ArrayList<>();
        for(int i =0; i<count;i++){
            int length = getRandomPasswordLength(maxPasswordLength);
            String password = getRandomPasswordGenerator().generate(length);
            String username = "user" + userCount++;
            users.add(new User(0, username, password));
        }
        return users;
    }

    private PasswordGenerator getRandomPasswordGenerator() {
        Random rand = new Random();
        PasswordGenerator randomGenerator = passwordGenerators.get(rand.nextInt(passwordGenerators.size()));
        return randomGenerator;
    }

    private static int getRandomPasswordLength(int maxPasswordLength) {
        Random random = new Random();
        int number = random.nextInt((maxPasswordLength - 1) + 1) + 1;
        return number;
    }
}
