package fr.balijon.centrale.entity.dto;

import fr.balijon.centrale.entity.Favorite;
import fr.balijon.centrale.entity.Listing;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<Listing> listings = new ArrayList<Listing>();

    private List<Favorite> favorites = new ArrayList<Favorite>();


}
