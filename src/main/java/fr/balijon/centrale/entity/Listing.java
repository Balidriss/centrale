package fr.balijon.centrale.entity;
import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.jsonviews.JsonViews;
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
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingListView.class})
    private String uuid;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class, JsonViews.ListingListView.class})
    private Long price;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class, JsonViews.ListingListView.class})
    private Long mileage;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingListView.class})
    private LocalDateTime producedAt;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingShow.class})
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "text")
    @JsonView(JsonViews.ListingShow.class)
    private String description;

    @Column(nullable = false)
    @JsonView({JsonViews.UserShow.class, JsonViews.ListingListView.class})
    private String title;

    //relations

    @OneToMany(mappedBy = "listing")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "listing", fetch = FetchType.EAGER)
    @JsonView({JsonViews.ListingListView.class})
    private List<Image> images = new ArrayList<Image>();

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private User owner;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private Address address;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private Model model;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private Fuel fuel;

    public void initTitle() {
        title = "Vente de " +
                getModel().getBrand().getName() +
                " " + getModel().getName() +
                " à " + getPriceDecimal();
    }

    @JsonView(JsonViews.ListingListView.class)
    public String getPriceDecimal() {
        return (this.price / 100.0d) + "€";
    }
}