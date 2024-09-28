package fr.balijon.centrale.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
public class UserListingId implements Serializable {


    @Column(name="user_uuid")
    private String userUuid;

    @Column(name="listing_uuid")
    private String listingUuid;
}
