package fr.balijon.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.jsonviews.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false, columnDefinition = "text")
    @JsonView({JsonViews.UserShow.class,JsonViews.ListingListView.class})
    private String path;


    //relations

    @ManyToOne
    @JoinColumn(nullable = false)
    private Listing listing;


}