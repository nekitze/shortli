package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
