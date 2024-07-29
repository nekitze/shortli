package edu.nikitazubov.shortli.util.admin;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AdminUtils {
    private static final Random random = new Random();
    public static boolean roll(float chance) {
        return random.nextFloat() < chance;
    }
}