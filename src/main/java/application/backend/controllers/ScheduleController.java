package application.backend.controllers;

import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.DTO.ScheduleDTO;
import application.backend.models.entities.FacilitySpace;
import application.backend.models.entities.Schedule;
import application.backend.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Schedule> saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO);
        return new ResponseEntity<Schedule>(schedule, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<Schedule> findSheduleByProgramId(@PathVariable("id") Long id) {
        Schedule shedule = scheduleService.findSheduleByProgramId(id);
        return new ResponseEntity<Schedule>(shedule, HttpStatus.OK);
    }

    @PutMapping(value = "/update/", consumes = "application/json")
    public ResponseEntity<Schedule> updateSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return new ResponseEntity<Schedule>(scheduleService.updateSchedule(scheduleDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return new ResponseEntity<String>("Facility " + id +" was deleted successfully",HttpStatus.OK);
    }
}
