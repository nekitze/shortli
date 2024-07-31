package edu.nikitazubov.shortli.repository;

import edu.nikitazubov.shortli.entity.admin.AdminStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AdminStatisticsRepository extends JpaRepository<AdminStatistics, LocalDate> {
}
