package application.backend.services;

import application.backend.models.DTO.ProgramDTO;
import application.backend.models.entities.Program;

import java.util.List;

public interface ProgramService {

    public List<Program> getAllPrograms();
    public Program getProgramById(Long id);
    public Program createProgram(ProgramDTO programDTO);
    public Program updateProgram(ProgramDTO programDTO);
    public void deleteProgram(Long id);
    public List<Program> getProgramByTrainer(Long userId);
}
