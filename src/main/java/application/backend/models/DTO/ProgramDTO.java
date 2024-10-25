package application.backend.models.DTO;

import application.backend.models.entities.Trainer;
import application.backend.models.enums.ProgramDuration;
import application.backend.models.enums.ProgramLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {

    private Long id;
    private String programDuration;
    private float price;
    private String name;
    private String description;
    private String programLevel;
    private Long trainerId;
    private String image;
    private Long facilityId;

}
