package application.backend.controllers;

import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.DTO.ProgramDTO;
import application.backend.models.entities.LoyaltyCard;
import application.backend.models.entities.Program;
import application.backend.models.entities.User;
import application.backend.repositories.ProgramRepository;
import application.backend.repositories.UserRepository;
import application.backend.services.ProgramService;
import application.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/")
    public ResponseEntity<?> createProgram(@RequestBody ProgramDTO programDTO) {
        Program program = programService.createProgram(programDTO);
        return new ResponseEntity<Program>(program, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<Program> findLProgramdById(@PathVariable("id") Long id) {
        Program program = programService.getProgramById(id);
        return new ResponseEntity<Program>(program, HttpStatus.OK);
    }

    @PostMapping("/{programId}/user/{username}/")
    public ResponseEntity<String> addProgramToUser(
            @PathVariable String username,
            @PathVariable Long programId) {
        Program program = programRepository.findById(programId).orElse(null);
        User user = userService.findByUsername(username);

        if (program.getParticipants().contains(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already part of this program.");
        }


        program.getParticipants().add(user);
        programService.addProgramToUser(program.getId(), user.getUsername());

        return ResponseEntity.ok("User successfully added to the program.");
    }


    @GetMapping(value = "/user/id/{id}/")
    public ResponseEntity<?> findProgramByUserId(@PathVariable("id") Long id) {
        List<Program> programs = programService.findProgramByUserId(id);
        return new ResponseEntity<List<Program>>(programs, HttpStatus.OK);
    }

    @GetMapping(value = "/user/username/{username}/")
    public ResponseEntity<?> findProgramByUsername(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        List<Program> programs = programService.findProgramByUserId(user.getId());
        return new ResponseEntity<List<Program>>(programs, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}/", consumes = "application/json")
    public ResponseEntity<Program> updateProgram(@RequestBody ProgramDTO programDTO, @PathVariable Long id) {
        return new ResponseEntity<Program>(programService.updateProgram(programDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteProgram(@PathVariable Long id) {
        programService.deleteById(id);
        return new ResponseEntity<String>("Program " + id +" was deleted successfully", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return new ResponseEntity<>(programs, HttpStatus.OK);
    }
}
