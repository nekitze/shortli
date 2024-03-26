package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
