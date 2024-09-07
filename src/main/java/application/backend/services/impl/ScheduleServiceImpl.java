package application.backend.services.impl;

import application.backend.models.DTO.ScheduleDTO;
import application.backend.models.entities.Schedule;
import application.backend.repositories.ScheduleRepository;
import application.backend.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findScheduleById(Long id) {
        return scheduleRepository.getById(id);
    }

    @Override
    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setName(scheduleDTO.getName());
        schedule.setDescription(scheduleDTO.getDescription());
        schedule.setScheduleTimes(scheduleDTO.getScheduleTimes());
        schedule.setProgram(scheduleDTO.getProgram());
        schedule.setFacilitySpace(scheduleDTO.getFacilitySpace());
        schedule.setDaysOfWeek(scheduleDTO.getDaysOfWeek());

        schedule = scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
