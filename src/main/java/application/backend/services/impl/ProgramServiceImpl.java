package application.backend.services.impl;

import application.backend.models.DTO.ProgramDTO;
import application.backend.models.entities.Program;
import application.backend.repositories.ProgramRepository;
import application.backend.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Program getProgramById(Long id) {
        return programRepository.findById(id).orElse(null);
    }

    @Override
    public Program createProgram(ProgramDTO programDTO) {
        Program program = new Program();

        program.setProgramDuration(programDTO.getProgramDuration());
        program.setPrice(programDTO.getPrice());
        program.setName(programDTO.getName());
        program.setDescription(programDTO.getDescription());
        program.setProgramLevel(programDTO.getProgramLevel());
        program.setTrainer(programDTO.getTrainer());

        programRepository.save(program);

        return program;
    }

    @Override
    public Program updateProgram(ProgramDTO programDTO) {

        Program updatedProgram = programRepository.findById(programDTO.getId()).orElse(null);
        if (updatedProgram != null) {
            updatedProgram.setProgramDuration(programDTO.getProgramDuration());
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
            updatedProgram.setProgramLevel(programDTO.getProgramLevel());
        }
        if (updatedProgram != null) {
            updatedProgram.setTrainer(programDTO.getTrainer());
        }

        programRepository.updateProgram(updatedProgram.getProgramDuration(), updatedProgram.getPrice(), updatedProgram.getName(), updatedProgram.getDescription(), updatedProgram.getProgramLevel(), updatedProgram.getTrainer(), updatedProgram.getId());

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
}
