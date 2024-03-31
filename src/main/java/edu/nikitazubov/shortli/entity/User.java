package edu.nikitazubov.shortli.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String role;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Url> ownedUrls;
}
