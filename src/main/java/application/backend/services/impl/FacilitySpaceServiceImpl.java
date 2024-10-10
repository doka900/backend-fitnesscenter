package application.backend.services.impl;

import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.entities.FacilitySpace;
import application.backend.models.enums.FacilitySpaceType;
import application.backend.repositories.FacilitySpaceRepository;
import application.backend.services.FacilityService;
import application.backend.services.FacilitySpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitySpaceServiceImpl implements FacilitySpaceService {

    @Autowired
    FacilitySpaceRepository facilitySpaceRepository;
    @Autowired
    FacilityService facilityService;

    @Override
    public FacilitySpace createFacilitySpace(FacilitySpaceDTO facilitySpaceDTO) {

        FacilitySpace facilitySpace = new FacilitySpace();

        facilitySpace.setName(facilitySpaceDTO.getName());
        facilitySpace.setCapacity(facilitySpaceDTO.getCapacity());
        facilitySpace.setType(FacilitySpaceType.valueOf(facilitySpaceDTO.getFacilitySpaceType().toString())) ;
        facilitySpace.setImage(facilitySpaceDTO.getImage());
        facilitySpace.setFacility(facilityService.findFacilityById(facilitySpaceDTO.getFacility_id()));
        facilitySpaceRepository.save(facilitySpace);


        return facilitySpace;
    }

    @Override
    public FacilitySpace updateFacilitySpace(FacilitySpaceDTO facilitySpaceDTO, Long id) {
        FacilitySpace updatedFacilitySpace = facilitySpaceRepository.findById(id).orElse(null);

        if(facilitySpaceDTO.getName() != null){
            updatedFacilitySpace.setName(facilitySpaceDTO.getName());
        }
        if(facilitySpaceDTO.getCapacity() != null){
            updatedFacilitySpace.setCapacity(facilitySpaceDTO.getCapacity());
        }
        if(facilitySpaceDTO.getFacilitySpaceType() != null) {
            updatedFacilitySpace.setType(facilitySpaceDTO.getFacilitySpaceType());
        }
        if(facilitySpaceDTO.getImage() != null) {
            updatedFacilitySpace.setImage(facilitySpaceDTO.getImage());
        }


        facilitySpaceRepository.updateFacilitySpace(updatedFacilitySpace.getName(), updatedFacilitySpace.getCapacity(), updatedFacilitySpace.getType().toString(), updatedFacilitySpace.getImage() , updatedFacilitySpace.getId());

        return updatedFacilitySpace;
    }

    @Override
    public FacilitySpace findFacilitySpaceById(Long id) {
        return facilitySpaceRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFacilityById(Long id) {
        facilitySpaceRepository.deleteById(id);
    }

    @Override
    public List<FacilitySpace> findByFacilityId(Long facilityId) {
        return facilitySpaceRepository.findByFacilityId(facilityId);
    }

    @Override
    public void deleteById(Long id) {
        facilitySpaceRepository.deleteById(id);
    }
}
