package fr.balijon.centrale.entity.dto;

import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.entity.Listing;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    @Past //cannot put a past date
    private LocalDate birthAt;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmedPassword;


}
