package application.backend.repositories;

import application.backend.models.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Repository findById(long id);
    List<Reservation> findByUserId(long id);

}
