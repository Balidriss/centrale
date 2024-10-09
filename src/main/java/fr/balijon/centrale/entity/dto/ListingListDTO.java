package fr.balijon.centrale.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListingListDTO {

    private String uuid;

    private String title;

    private String price;

    private Long mileage;

    private LocalDateTime producedAt;

    private String zipCode;

    private String model;

    private String brand;

    private String fuel;

}