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
    @JsonView(JsonViews.AddressShow.class)
    private Long id;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingShow.class,JsonViews.AddressListView.class})
    private String streetNumber;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingShow.class,JsonViews.AddressListView.class})
    private String streetName;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingShow.class,JsonViews.AddressListView.class})
    private String zipCode;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingListView.class,JsonViews.AddressListView.class})
    private String city;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingShow.class,JsonViews.AddressListView.class})
    private String latitude;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingShow.class,JsonViews.AddressListView.class})
    private String longitude;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "address")
    private List<Listing> listings = new ArrayList<Listing>();
}