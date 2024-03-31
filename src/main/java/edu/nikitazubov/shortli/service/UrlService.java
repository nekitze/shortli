package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;

import java.util.List;

public interface UrlService {
    List<Url> getAllUrls();

    Url getUrlById(Long id);

    Url getUrlByShortUrl(String shortUrl);

    Url addNewUrl(Url url);

    Url updateUrl(Url url);

    String deleteUrl(Long id);
}
