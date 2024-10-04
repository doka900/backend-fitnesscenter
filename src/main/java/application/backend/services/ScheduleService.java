package application.backend.services;

import application.backend.models.DTO.ScheduleDTO;
import application.backend.models.entities.Schedule;

import java.util.List;

public interface ScheduleService {

    public List<Schedule> findAllSchedules();
    public Schedule findScheduleById(Long id);
    public Schedule createSchedule(ScheduleDTO scheduleDTO);
    public Schedule updateSchedule(ScheduleDTO scheduleDTO);
    public void deleteSchedule(Long id);

    public Schedule findSheduleByProgramId(Long id);

    void deleteById(Long id);
}
