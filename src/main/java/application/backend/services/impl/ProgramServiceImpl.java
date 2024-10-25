package application.backend.services.impl;

import application.backend.models.DTO.ProgramDTO;
import application.backend.models.entities.Facility;
import application.backend.models.entities.Program;
import application.backend.models.entities.User;
import application.backend.models.enums.ProgramDuration;
import application.backend.models.enums.ProgramLevel;
import application.backend.repositories.FacilityRepository;
import application.backend.repositories.ProgramRepository;
import application.backend.repositories.UserRepository;
import application.backend.services.ProgramService;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Program getProgramById(Long id) {
        return programRepository.findById(id).orElse(null);
    }

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public Program createProgram(ProgramDTO programDTO) {


        Optional<Facility> facilityOpt = facilityRepository.findById(programDTO.getFacilityId());
        if(facilityOpt.isPresent()) {
            Program program = new Program();
            
            Facility facility = facilityOpt.get();

            program.setFacility(facility);
            program.setProgramDuration(ProgramDuration.valueOf(programDTO.getProgramDuration()));
            program.setPrice(programDTO.getPrice());
            program.setName(programDTO.getName());
            program.setDescription(programDTO.getDescription());
            program.setProgramLevel(ProgramLevel.valueOf(programDTO.getProgramLevel()));
            program.setTrainer((userRepository.findTrainerById(programDTO.getTrainerId())));
            program.setImage(programDTO.getImage());
            programRepository.save(program);


            System.out.println("Setting up trainer with id" + programDTO.getTrainerId() + "  res " + programDTO.getTrainerId());
            return program;}
        else { throw new RuntimeException("Facility not found");}
    }

    @Override
    public Program updateProgram(ProgramDTO programDTO, Long id) {

        System.out.println("TEST PROGRAM DTO programDTO" + id + " " +programDTO.getId());


        Program updatedProgram = programRepository.findById(programDTO.getId()).orElse(null);
        Facility facility = facilityRepository.findById(programDTO.getFacilityId()).orElseThrow();
        updatedProgram.setFacility(facility);

        if (updatedProgram != null) {
            System.out.println("duration: " + programDTO.getProgramDuration().toString());
            updatedProgram.setProgramDuration(ProgramDuration.valueOf(programDTO.getProgramDuration()));


        }
        if (updatedProgram != null) {
            updatedProgram.setPrice(programDTO.getPrice());
        }
        if (updatedProgram != null) {
            updatedProgram.setName(programDTO.getName());
        }
        if (updatedProgram != null) {
            updatedProgram.setDescription(programDTO.getDescription());
        }
        if (updatedProgram != null) {
            updatedProgram.setProgramLevel(ProgramLevel.valueOf(programDTO.getProgramLevel()));
        }
        if (updatedProgram != null) {
            System.out.println( "finding trainer with id : " +programDTO.getTrainerId() +" res :" + userRepository.findTrainerById(programDTO.getTrainerId()));
            updatedProgram.setTrainer(userRepository.findTrainerById(programDTO.getTrainerId()));
        }
        if (updatedProgram != null) {
            updatedProgram.setImage(programDTO.getImage());
        }

        programRepository.updateProgram(updatedProgram.getProgramDuration().ordinal(), updatedProgram.getPrice(), updatedProgram.getName(), updatedProgram.getDescription(), updatedProgram.getProgramLevel().ordinal(), updatedProgram.getTrainer().getId(), updatedProgram.getImage(),updatedProgram.getId());

        return updatedProgram;
    }

    @Override
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public List<Program> getProgramByTrainer(Long userId) {
        return programRepository.getByUserId(userId);
    }

    @Override
    public void deleteById(Long id) {
        programRepository.deleteById(id);
    }

    @Override
    public List<Program> findProgramByUserId(Long id) {
        return programRepository.getByUserId(id);
    }

    @Override
    public Program addProgramToUser(Long programId, String username) {
        Program program = programRepository.findById(programId).orElseThrow(() -> new ResourceNotFoundException("Program not found"));
        User user = userRepository.findByUsername(username);

        program.getParticipants().add(user);  // Add user to participants set
        return programRepository.save(program);  // Save the program with updated participants
    }
}
