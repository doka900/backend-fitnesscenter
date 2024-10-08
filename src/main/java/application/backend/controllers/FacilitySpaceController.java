package application.backend.controllers;

import application.backend.models.DTO.FacilityDTO;
import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.entities.Facility;
import application.backend.models.entities.FacilitySpace;
import application.backend.models.enums.FacilitySpaceType;
import application.backend.models.enums.FacilityType;
import application.backend.services.FacilitySpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/facilitySpace")
public class FacilitySpaceController {

    @Autowired
    private FacilitySpaceService facilityService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<FacilitySpace> saveFacilitySpace(@RequestBody FacilitySpaceDTO facilitySpaceDTO) {
        FacilitySpace newFacilitySpace = facilityService.createFacilitySpace(facilitySpaceDTO);
        return new ResponseEntity<FacilitySpace>(newFacilitySpace, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<List<FacilitySpace>> findFacilitySpacesByFacilityId(@PathVariable("id") Long id) {
        List<FacilitySpace> facilitySpaces = facilityService.findByFacilityId(id);
        return new ResponseEntity<List<FacilitySpace>>(facilitySpaces, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}/", consumes = "application/json")
    public ResponseEntity<FacilitySpace> updateFacilitySpace(@PathVariable Long id, @RequestBody FacilitySpaceDTO facilitySpaceDTO) {
        return new ResponseEntity<FacilitySpace>(facilityService.updateFacilitySpace(facilitySpaceDTO, id), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        facilityService.deleteById(id);
        return new ResponseEntity<String>("Facility " + id +" was deleted successfully",HttpStatus.OK);
    }
    @GetMapping(value = "/types/")
    public ResponseEntity<List<FacilitySpaceType>> getFacilitySpaceTypes() {
        List<FacilitySpaceType> types = Arrays.asList(FacilitySpaceType.values());
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

}
