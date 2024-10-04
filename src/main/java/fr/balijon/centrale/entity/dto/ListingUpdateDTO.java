package fr.balijon.centrale.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListingUpdateDTO {

    @NotBlank
    private String description;

    @NotNull
    private Long price;

    @NotNull
    private Long mileage;

}
