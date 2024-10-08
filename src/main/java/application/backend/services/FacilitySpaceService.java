package application.backend.services;

import application.backend.models.DTO.FacilityDTO;
import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.entities.Facility;
import application.backend.models.entities.FacilitySpace;

import java.util.List;

public interface FacilitySpaceService {

    public FacilitySpace createFacilitySpace(FacilitySpaceDTO facilitySpaceDTO);

    public FacilitySpace updateFacilitySpace(FacilitySpaceDTO facilitySpaceDTO, Long id);

    public FacilitySpace findFacilitySpaceById(Long id);

    public void deleteFacilityById(Long id);

    public List<FacilitySpace> findByFacilityId(Long facilityId);

    public  void deleteById(Long id);
}
