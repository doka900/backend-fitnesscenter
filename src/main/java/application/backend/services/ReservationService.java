package application.backend.services;

import application.backend.models.DTO.ReservationDTO;
import application.backend.models.entities.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> findAllReservations();

    public Reservation createReservation(ReservationDTO reservationDTO);
    public Reservation updateReservation(ReservationDTO reservationDTO);
    public Reservation findReservationById(Long id);
    public void deleteReservationById(Long id);
}
