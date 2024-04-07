package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.Url;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findUrlByKey(String key);

    List<Url> findUrlsByOwnerId(Long id);

    @Modifying
    @Query("update Url u set u.visitsCount = u.visitsCount + 1 where u.key = :key")
    void incrementVisitsCountByKey(@Param("key") String key);
}
