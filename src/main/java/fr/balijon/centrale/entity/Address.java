package fr.balijon.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.jsonviews.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private String streetNumber;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private String streetName;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private String zipCode;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private String city;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private Long latitude;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private Long longitude;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "address")
    private List<Listing> listings = new ArrayList<Listing>();
}