package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    public Optional<Url> findUrlByKey(String key);
    public List<Url> findUrlsByOwnerId(Long id);
}
