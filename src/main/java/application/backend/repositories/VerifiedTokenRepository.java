package application.backend.repositories;

import application.backend.security.VerifiedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifiedTokenRepository extends JpaRepository<VerifiedToken, Long> {

    VerifiedToken findByToken(String token);
}
