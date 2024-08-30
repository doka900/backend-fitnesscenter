package application.backend.services.impl;

import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.entities.FacilitySpace;
import application.backend.repositories.FacilitySpaceRepository;
import application.backend.services.FacilitySpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitySpaceServiceImpl implements FacilitySpaceService {

    @Autowired
    FacilitySpaceRepository facilitySpaceRepository;

    @Override
    public FacilitySpace createFacilitySpace(FacilitySpaceDTO facilitySpaceDTO) {

        FacilitySpace facilitySpace = new FacilitySpace();

        facilitySpace.setName(facilitySpaceDTO.getName());
        facilitySpace.setCapacity(facilitySpaceDTO.getCapacity());
        facilitySpace.setFacility(facilitySpaceDTO.getFacility());
        facilitySpace.setType(facilitySpaceDTO.getFacilitySpaceType());

        facilitySpaceRepository.save(facilitySpace);

        return facilitySpace;
    }

    @Override
    public FacilitySpace updateFacilitySpace(FacilitySpaceDTO facilitySpaceDTO) {
        FacilitySpace updatedFacilitySpace = facilitySpaceRepository.findById(facilitySpaceDTO.getId()).orElse(null);

        if(facilitySpaceDTO.getName() != null){
            updatedFacilitySpace.setName(facilitySpaceDTO.getName());
        }
        if(facilitySpaceDTO.getCapacity() != null){
            updatedFacilitySpace.setCapacity(facilitySpaceDTO.getCapacity());
        }
        if(facilitySpaceDTO.getFacilitySpaceType() != null) {
            updatedFacilitySpace.setType(facilitySpaceDTO.getFacilitySpaceType());
        }

        facilitySpaceRepository.updateFacilitySpace(updatedFacilitySpace.getName(), updatedFacilitySpace.getCapacity(), updatedFacilitySpace.getType() , updatedFacilitySpace.getId());

        return updatedFacilitySpace;
    }

    @Override
    public FacilitySpace findFacilitySpaceById(long id) {
        return facilitySpaceRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFacilityById(long id) {
        facilitySpaceRepository.deleteById(id);
    }

    @Override
    public List<FacilitySpace> findByFacilityId(Long facilityId) {
        return facilitySpaceRepository.findByFacilityId(facilityId);
    }
}
