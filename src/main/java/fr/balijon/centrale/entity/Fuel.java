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
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.FuelListView.class)
    private Long id;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.FuelListView.class})
    private String type;

    @Column(nullable = false)
    @JsonView(JsonViews.FuelListView.class)
    private String logo;

    //relations

    @OneToMany(mappedBy = "fuel")
    private List<Listing> listings = new ArrayList<Listing>();
}