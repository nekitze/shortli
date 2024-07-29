package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.admin.AdminParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminParameterRepository extends JpaRepository<AdminParameter, String> {
}