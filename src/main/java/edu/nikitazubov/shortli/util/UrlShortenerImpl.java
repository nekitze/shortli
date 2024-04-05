package edu.nikitazubov.shortli.util;

import org.springframework.stereotype.Component;

@Component
public class UrlShortenerImpl implements UrlShortener {

    @Override
    public String shorten(String longUrl) {
        return "shorturl.domain/ABCDEF123";
    }
}
