package application.backend.models.entities;


import application.backend.models.enums.ProgramDuration;
import application.backend.models.enums.ProgramLevel;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "program_duration")
    private ProgramDuration programDuration;

    @Column
    private float price;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "program_level")
    private ProgramLevel programLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    @JsonBackReference
    private User trainer;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToMany
    @JoinTable(
            name = "program_participant",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonManagedReference
    private Set<User> participants = new HashSet<>();


}
