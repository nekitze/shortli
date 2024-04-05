package edu.nikitazubov.shortli.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Url> ownedUrls;

    public void addUrlToUser(Url url) {
        if (ownedUrls == null) {
            ownedUrls = new ArrayList<>();
        }
        ownedUrls.add(url);
    }
}
