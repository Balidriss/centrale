package fr.balijon.centrale.entity.dto;

import fr.balijon.centrale.entity.Listing;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDTO {


    @NotBlank
    private String path;

    @NotBlank
    private String listingUuid;


}
