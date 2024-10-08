package application.backend.controllers;

import application.backend.models.DTO.CompanyDTO;
import application.backend.models.DTO.FacilityDTO;
import application.backend.models.entities.Company;
import application.backend.models.entities.Facility;
import application.backend.models.enums.FacilityType;
import application.backend.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/facility")
public class FacilityController {

    @Autowired
    FacilityService facilityService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Facility>> findAllFacilities() {
        return new ResponseEntity<>(facilityService.findAllFacilities(), HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Facility> saveFacility(@RequestBody FacilityDTO facilityDTO) {
        Facility newFacility = facilityService.createFacility(facilityDTO);
        return new ResponseEntity<Facility>(newFacility, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<Facility> findFacilityById(@PathVariable("id") Long id) {
        Facility facility = facilityService.findFacilityById(id);
        return new ResponseEntity<Facility>(facility, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}/", consumes = "application/json")
    public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody FacilityDTO facilityDTO) {
        return new ResponseEntity<Facility>(facilityService.updateFacility(facilityDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteFacility(@PathVariable Long id) {
        facilityService.deleteById(id);
        return new ResponseEntity<String>("Facility " + id +" was deleted successfully",HttpStatus.OK);
    }

    @GetMapping(value = "/types/")
    public ResponseEntity<List<FacilityType>> getFacilityTypes() {
        List<FacilityType> types = Arrays.asList(FacilityType.values());
        return new ResponseEntity<>(types, HttpStatus.OK);
    }
}
