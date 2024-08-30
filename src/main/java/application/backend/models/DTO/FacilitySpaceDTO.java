package application.backend.models.DTO;

import application.backend.models.entities.Facility;
import application.backend.models.entities.FacilitySpace;
import application.backend.models.enums.FacilitySpaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilitySpaceDTO {

    private Long id;
    private String name;
    private Long capacity;
    private FacilitySpaceType facilitySpaceType;
    private Facility facility;
}
