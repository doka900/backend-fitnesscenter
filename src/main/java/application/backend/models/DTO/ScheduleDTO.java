package application.backend.models.DTO;

import application.backend.models.entities.FacilitySpace;
import application.backend.models.entities.Program;
import application.backend.models.enums.DaysOfWeek;
import application.backend.models.enums.ScheduleTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long id;
    private String name;
    private String description;
    private Program program;
    private Set<DaysOfWeek> daysOfWeek;
    private Set<ScheduleTime> scheduleTimes;
    private FacilitySpace facilitySpace;
}
