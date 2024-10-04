package application.backend.controllers;

import application.backend.models.DTO.CompanyDTO;
import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.entities.Company;
import application.backend.models.entities.LoyaltyCard;
import application.backend.services.LoyaltyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/loyaltyCard")
public class LoyaltyCardController {

    @Autowired
    private LoyaltyCardService loyaltyCardService;

    @GetMapping(value = "/{id}/")
    public ResponseEntity<LoyaltyCard> findLoyaltyCardById(@PathVariable("id") Long id) {
        LoyaltyCard loyaltyCard = loyaltyCardService.findById(id);
        return new ResponseEntity<LoyaltyCard>(loyaltyCard, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/")
    public ResponseEntity<LoyaltyCard> findLoyaltyCardByUserId(@PathVariable("id") Long id) {
        LoyaltyCard loyaltyCard = loyaltyCardService.findByUserId(id);
        return new ResponseEntity<LoyaltyCard>(loyaltyCard, HttpStatus.OK);
    }

    @PutMapping(value = "/update/", consumes = "application/json")
    public ResponseEntity<LoyaltyCard> updateLoyaltyCard(@RequestBody LoyaltyCardDTO loyaltyCardDTO) {
        return new ResponseEntity<LoyaltyCard>(loyaltyCardService.updateLoyaltyCard(loyaltyCardDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteLoyaltyCard(@PathVariable Long id) {
        loyaltyCardService.deleteById(id);
        return new ResponseEntity<String>("LoyaltyCard " + id +" was deleted successfully",HttpStatus.OK);
    }
}
