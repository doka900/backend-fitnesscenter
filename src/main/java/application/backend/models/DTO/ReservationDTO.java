package application.backend.models.DTO;

import application.backend.models.entities.FacilitySpace;
import application.backend.models.entities.Program;
import application.backend.models.entities.Schedule;
import application.backend.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;
    private User user;
    private Program program;
    private LocalDateTime reservationDateTime;
    private FacilitySpace facilitySpace;
    private Schedule schedule;
    private Boolean scheduled;

}
