package application.backend.repositories;

import application.backend.models.entities.Program;
import application.backend.models.entities.Trainer;
import application.backend.models.entities.User;
import application.backend.models.enums.ProgramDuration;
import application.backend.models.enums.ProgramLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p JOIN p.participants u WHERE u.id = :userId")
    List<Program> getByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE programs p SET p.program_duration = ?1, p.price = ?2, p.name = ?3, p.description = ?4, p.program_level = ?5, p.trainer_id = ?6, p.image = ?7 WHERE p.id = ?8", nativeQuery = true)
    void updateProgram(int programDuration, float price, String name, String description, int programLevel, Long trainer_id, String image, Long programId);

    @Query(value = "SELECT * FROM program_participant WHERE trainer_id = :trainerId", nativeQuery = true)
    List<Program> getByTrainerId(@Param("trainerId") Long trainerId);

    @Modifying
    @Query("UPDATE Program p SET p.trainer = null WHERE p.trainer.id = :trainerId")
    void removeTrainerFromPrograms(@Param("trainerId") Long trainerId);

}
