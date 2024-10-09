package fr.balijon.centrale.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandListDTO {

    private Long id;

    private String name;

    private Integer modelCount;
}
