package edu.nikitazubov.shortli.entity.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "admin_parameters")
public class AdminParameter {
    @Id
    private String key;

    private String value;
}