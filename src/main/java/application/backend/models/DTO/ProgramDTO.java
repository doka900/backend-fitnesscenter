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
    private ProgramDuration programDuration;
    private float price;
    private String name;
    private String description;
    private ProgramLevel programLevel;
    private Trainer trainer;

}
