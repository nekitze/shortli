package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    public Optional<Url> findUrlByShortUrl(String shortUrl);
}
