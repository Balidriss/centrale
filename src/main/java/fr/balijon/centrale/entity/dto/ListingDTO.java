package fr.balijon.centrale.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.entity.Address;
import fr.balijon.centrale.entity.Model;
import fr.balijon.centrale.jsonviews.JsonViews;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListingDTO {

    @NotBlank
    private String description;

    @NotNull
    private Long price;

    @NotNull
    private Long mileage;

    @NotBlank
    private String title;



    @NotNull
    private LocalDateTime producedAt;

    @NotNull
    private Long addressId;

    @NotNull
    private Long fuelId;

    @NotNull
    private Long modelId;


}
