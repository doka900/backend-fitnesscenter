package application.backend.repositories;

import application.backend.models.entities.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {

    LoyaltyCard findById(long id);
    void deleteById(long id);

    LoyaltyCard findByUserId(Long id);
}
