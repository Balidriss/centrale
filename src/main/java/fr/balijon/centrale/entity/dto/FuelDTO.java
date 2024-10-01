package fr.balijon.centrale.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuelDTO {

    @NotBlank
    private String logo;
    @NotBlank
    private String type;
}
