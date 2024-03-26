package edu.nikitazubov.shortli.util;

public interface UrlShortener {
    String shorten(String longUrl);
    String longUrlOf(String shortUrl);
}
