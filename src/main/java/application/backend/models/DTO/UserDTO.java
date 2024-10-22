package application.backend.models.DTO;

import application.backend.models.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private LocalDate dateOfBirth;

    private String name;

    private String surname;

    private LocalDate registrationDate;

    private String description;

    private String display_name;

    private String profileImage;

    private Roles role;

    private String type;
}
