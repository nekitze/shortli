package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.repository.UrlRepository;
import edu.nikitazubov.shortli.util.UrlShortener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlShortener urlShortener;

    @Override
    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    @Override
    public Url getUrlById(Long id) {
        return urlRepository.findById(id).orElse(null);
    }

    @Override
    public Url getUrlByShortUrl(String shortUrl) {
        return urlRepository.findUrlByShortUrl(shortUrl).orElse(null);
    }

    @Override
    public Url addNewUrl(String fullUrl) {
        Url url = new Url();
        url.setFullUrl(fullUrl);
        url.setShortUrl(urlShortener.shorten(fullUrl));
        url.setOwnerId(null);
        return urlRepository.save(url);
    }

    @Override
    public Url updateUrl(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public String deleteUrl(Long id) {
        urlRepository.deleteById(id);
        return "deleted";
    }
}
