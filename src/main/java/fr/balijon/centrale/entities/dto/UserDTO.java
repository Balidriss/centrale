package fr.balijon.centrale.entities.dto;

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
public class UserDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    @NotNull
    private LocalDateTime birthAt;

    @NotBlank
    private String role;

    private String photo = null;

    private String activationCode = null;

    private String siret = null;
}
