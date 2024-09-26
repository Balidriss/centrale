package fr.balijon.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.jsonviews.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
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
    @JsonView(JsonViews.UserMinimalView.class)
    private String uuid;

    @Column(nullable = false)
    @JsonView(JsonViews.UserMinimalView.class)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private String phone;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private LocalDate birthAt;

    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViews.UserShow.class)
    private String photo = null;

    private String activationCode = null;

    @JsonView(JsonViews.UserShow.class)
    private String siret = null;

    //relations

    @OneToOne(mappedBy = "user")
    @JsonView(JsonViews.UserShow.class)
    private Address address;

    @OneToMany(mappedBy = "owner")
    private List<Listing> listings = new ArrayList<Listing>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites = new ArrayList<Favorite>();

    ////

    @JsonView(JsonViews.UserMinimalView.class)
    public Boolean isActive()
    {
        return activationCode == null;
    }

}