package edu.nikitazubov.shortli.entity.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "admin_statistics")
public class AdminStatistics {
    @Id
    private LocalDate date;

    private long dailyVisits;

    private long adShows;
}
