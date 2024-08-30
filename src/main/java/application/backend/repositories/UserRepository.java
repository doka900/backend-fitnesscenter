package application.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import application.backend.models.entities.User;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findUserById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update users u set u.password = ?1 where u.id=?2 ", nativeQuery = true)
    void changePassword(String password, Long id);

    @Modifying
    @Transactional
    @Query(value = "update users u set u.email = ?1, u.date_of_birth = ?2, u.name = ?3 , u.surname = ?4 ,u.description = ?5, u.display_name = ?6, u.profile_image = ?7 where u.id = ?8", nativeQuery = true)
    void updateUser(String email, LocalDate dateOfBirth, String name, String surname ,String description, String display_name, byte[] profile_image, long id);

}
