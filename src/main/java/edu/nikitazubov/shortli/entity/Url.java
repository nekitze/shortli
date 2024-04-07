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

    @Lob
    private String fullUrl;

    private Long visitsCount;

    @Column(name = "owner_id")
    private Long ownerId;

    public void setFullUrl(String fullUrl) {
        if (!fullUrl.startsWith("http://") && !fullUrl.startsWith("https://")) {
            fullUrl = "http://" + fullUrl;
        }
        this.fullUrl = fullUrl;
    }
}
