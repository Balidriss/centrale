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
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.ModelListView.class,JsonViews.BrandListView.class})
    private String name;

    //relations

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    @JsonView(JsonViews.BrandShow.class)
    private List<Model> models = new ArrayList<Model>();

    //

    @JsonView(JsonViews.BrandListView.class)
    public Integer modelCount()
    {
        return models.size();
    }
}