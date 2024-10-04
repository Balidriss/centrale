package fr.balijon.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.embedded.UserListingId;
import fr.balijon.centrale.jsonviews.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Favorite {

    @EmbeddedId
    private UserListingId id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShow.class)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_uuid", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "listing_uuid", insertable = false, updatable = false)
    @JsonView(JsonViews.UserShow.class)
    private Listing listing;
}