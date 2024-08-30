package application.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import application.backend.models.entities.User;

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
    @Query(value = "update users u set u.email = ?1, u.description = ?2, u.display_name = ?3, u.profile_image = ?4 where u.id = ?5", nativeQuery = true)
    void updateUser(String email, String description, String display_name, byte[] profile_image, long id);

}
