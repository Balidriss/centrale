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
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({JsonViews.ModelListView.class,JsonViews.BrandShow.class})
    private Long id;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.ModelListView.class,JsonViews.BrandShow.class})
    private String name;

    //relations

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.ModelListView.class})
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Listing> listings = new ArrayList<Listing>();



}