package application.backend.models.entities;

import application.backend.models.enums.FacilitySpaceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facility_spaces")
public class FacilitySpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String name;
    @Column
    private Long capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FacilitySpaceType type;

    @Column(name = "image")
    private String image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

}
