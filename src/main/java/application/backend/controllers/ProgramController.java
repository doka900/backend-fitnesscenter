package application.backend.controllers;

import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.DTO.ProgramDTO;
import application.backend.models.entities.LoyaltyCard;
import application.backend.models.entities.Program;
import application.backend.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping(value = "/{id}/")
    public ResponseEntity<Program> findLProgramdById(@PathVariable("id") Long id) {
        Program program = programService.getProgramById(id);
        return new ResponseEntity<Program>(program, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/")
    public ResponseEntity<List<Program>> findProgramByUserId(@PathVariable("id") Long id) {
        List<Program> programs = programService.findProgramByUserId(id);
        return new ResponseEntity<List<Program>>(programs, HttpStatus.OK);
    }

    @PutMapping(value = "/update/", consumes = "application/json")
    public ResponseEntity<Program> updateProgram(@RequestBody ProgramDTO programDTO) {
        return new ResponseEntity<Program>(programService.updateProgram(programDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteProgram(@PathVariable Long id) {
        programService.deleteById(id);
        return new ResponseEntity<String>("Program " + id +" was deleted successfully", HttpStatus.OK);
    }

}
