package application.backend.services.impl;

import application.backend.models.DTO.ReservationDTO;
import application.backend.models.entities.Reservation;
import application.backend.repositories.ReservationRepository;
import application.backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServisImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {

        Reservation reservation = new Reservation();
        reservation.setUser(reservationDTO.getUser());
        reservation.setReservationDateTime(reservationDTO.getReservationDateTime());
        reservation.setProgram(reservationDTO.getProgram());
        reservation.setSchedule(reservationDTO.getSchedule());
        reservation.setFacilitySpace(reservationDTO.getFacilitySpace());
        reservation.setScheduled(reservationDTO.getScheduled());

        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findReservationByUserId(Long id) {
        return reservationRepository.findByUserId(id);
    }
}
