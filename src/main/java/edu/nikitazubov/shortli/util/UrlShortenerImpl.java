package edu.nikitazubov.shortli.util;

import org.springframework.stereotype.Component;

import java.util.random.RandomGenerator;

@Component
public class UrlShortenerImpl implements UrlShortener {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static final int TOKEN_LENGTH = 6;

    @Override
    public String shorten(String longUrl) {
        return getRandomKey();
    }

    private String getRandomKey() {
        char[] key = new char[TOKEN_LENGTH];
        for(int i = 0; i < TOKEN_LENGTH; i++) {
            int randomIndex = RandomGenerator.getDefault().nextInt(0, CHARACTERS.length());
            key[i] = CHARACTERS.charAt(randomIndex);
        }
        return String.valueOf(key);
    }
}
