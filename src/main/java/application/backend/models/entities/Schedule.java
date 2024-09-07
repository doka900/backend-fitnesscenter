package application.backend.models.entities;

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
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @ElementCollection(targetClass = DaysOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day_of_week")
    private Set<DaysOfWeek> daysOfWeek;

    @ElementCollection(targetClass = ScheduleTime.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "schedule_times", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "schedule_time")
    private Set<ScheduleTime> scheduleTimes;

    @ManyToOne
    @JoinColumn(name = "facility_space_id", nullable = false)
    private FacilitySpace facilitySpace;
}
