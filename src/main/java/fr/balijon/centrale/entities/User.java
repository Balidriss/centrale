package fr.balijon.centrale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime birthAt;

    @Column(nullable = false)
    private String role;

    private String photo = null;

    private String activationCode = null;

    private String siret = null;

    //relations

    @OneToMany(mappedBy = "owner")
    private List<Listing> listings = new ArrayList<Listing>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<Favorite>();


    @OneToOne
    private User user;
}