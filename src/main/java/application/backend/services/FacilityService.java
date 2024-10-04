package application.backend.services;

import application.backend.models.DTO.FacilityDTO;
import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.entities.Facility;
import application.backend.models.entities.FacilitySpace;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FacilityService {

    public List<Facility> findAllFacilities();

    public Facility createFacility(FacilityDTO facilityDTO);

    public Facility updateFacility(FacilityDTO facilityDTO);

    public Facility findFacilityById(Long id);

    public void deleteById(Long id);
}
