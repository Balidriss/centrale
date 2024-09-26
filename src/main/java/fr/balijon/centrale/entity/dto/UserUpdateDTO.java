package fr.balijon.centrale.entity.dto;

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
public class UserUpdateDTO {

    @NotNull
    @Past
    private LocalDate birthAt;

    @NotBlank
    private String phone;

    private String siret;

    private String photo;

}