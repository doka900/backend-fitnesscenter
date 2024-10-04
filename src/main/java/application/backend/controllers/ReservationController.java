package application.backend.controllers;

import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.DTO.ReservationDTO;
import application.backend.models.entities.LoyaltyCard;
import application.backend.models.entities.Reservation;
import application.backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "/{id}/")
    public ResponseEntity<Reservation> findReservationById(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.findReservationById(id);
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Reservation>> findAllReservations() {
        List<Reservation> reservations = reservationService.findAllReservations();
        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/")
    public ResponseEntity<List<Reservation>> findReservationByUserId(@PathVariable("id") Long id) {
        List<Reservation> reservations = reservationService.findReservationByUserId(id);
        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
    }

    @PutMapping(value = "/update/", consumes = "application/json")
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationDTO reservationDTO) {
        return new ResponseEntity<Reservation>(reservationService.updateReservation(reservationDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return new ResponseEntity<String>("Reservation " + id +" was deleted successfully",HttpStatus.OK);
    }

}
