package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.repository.UrlRepository;
import edu.nikitazubov.shortli.repository.UserRepository;
import edu.nikitazubov.shortli.util.UrlShortener;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlShortener urlShortener;
    private final UserRepository userRepository;

    @Override
    public List<Url> getAllUrls() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            User currentUser = userRepository.findUserByEmail(authentication.getName()).get();
            return urlRepository.findUrlsByOwnerId(currentUser.getId());
        } else {
            return null;
        }
    }

    @Override
    public Url getUrlById(Long id) {
        return urlRepository.findById(id).orElse(null);
    }

    @Override
    public Url getUrlByKey(String key) {
        return urlRepository.findUrlByKey(key).orElse(null);
    }

    @Override
    public Url addNewUrl(String fullUrl) {
        Url url = new Url();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            User currentUser = userRepository.findUserByEmail(authentication.getName()).get();
            url.setOwnerId(currentUser.getId());
        }
        url.setFullUrl(fullUrl);
        url.setKey(urlShortener.shorten(fullUrl));
        return urlRepository.save(url);
    }

    @Override
    public Url updateUrl(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public void deleteUrl(Long id) {
        urlRepository.deleteById(id);
    }
}
