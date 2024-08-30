package application.backend.services;

import application.backend.models.DTO.FacilityDTO;
import application.backend.models.entities.Facility;

import java.util.List;

public interface FacilityService {

    public List<Facility> findAllFacilities();

    public Facility createFacility(FacilityDTO facilityDTO);

    public Facility updateFacility(FacilityDTO facilityDTO);

    public Facility findFacilityById(long id);

    public void deleteFacilityById(long id);
}
