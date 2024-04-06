package edu.nikitazubov.shortli.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String key;

    private String fullUrl;

    private Long visitsCount;

    @Column(name = "owner_id")
    private Long ownerId;
}
