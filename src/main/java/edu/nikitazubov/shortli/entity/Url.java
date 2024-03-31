package edu.nikitazubov.shortli.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shortUrl;

    private String fullUrl;

    private Long visitsCount;

    @Column(name = "owner_id")
    private Long ownerId;
}
