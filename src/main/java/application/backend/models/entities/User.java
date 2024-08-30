package application.backend.models.entities;

import java.time.LocalDate;

import javax.persistence.*;

import application.backend.models.enums.Roles;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column(name = "date_of_birth", columnDefinition = "BLOB")
    private LocalDate dateOfBirth;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column
    private String description;

    @Column(name = "display_name")
    private String displayName;

    @Lob
    @Column(name = "profile_image", columnDefinition = "BLOB")
    private byte[] profileImage;

    @Column
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LoyaltyCard loyaltyCard;

}