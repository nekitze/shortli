package edu.nikitazubov.shortli.entity;

import jakarta.persistence.*;

@Entity
public class Url {
    @Id
    private String shortUrl;
    private String fullUrl;
    private Long visitsCount;
    private Long ownerId;
}
