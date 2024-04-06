package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;

import java.util.List;

public interface UrlService {
    List<Url> getAllUrls();

    Url getUrlById(Long id);

    Url getUrlByKey(String shortKey);

    Url addNewUrl(String fullUrl);

    Url updateUrl(Url url);

    void deleteUrl(Long id);
}
