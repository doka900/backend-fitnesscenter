package application.backend.repositories;

import application.backend.models.entities.Program;
import application.backend.models.entities.Trainer;
import application.backend.models.enums.ProgramDuration;
import application.backend.models.enums.ProgramLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query(value = "select * from program_participant p where p.user_id = ?1", nativeQuery = true)
    List<Program> getByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "update programs p set p.programDuration = ?1, p.price = ?2, p.name = ?3 , p.description = ?4 ,p.programLevel = ?5, p.trainer = ?6 where p.id = ?7", nativeQuery = true)
    void updateProgram(ProgramDuration programDuration, float price, String name, String description, ProgramLevel programLevel, Trainer trainer, Long programId);
}
