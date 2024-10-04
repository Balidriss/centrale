package fr.balijon.centrale.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.balijon.centrale.jsonviews.JsonViews;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String streetName;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotNull
    private String latitude;

    @NotNull
    private String longitude;

}
