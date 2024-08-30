package application.backend.services.impl;

import application.backend.models.DTO.FacilityDTO;
import application.backend.models.DTO.FacilitySpaceDTO;
import application.backend.models.entities.Facility;
import application.backend.models.entities.FacilitySpace;
import application.backend.repositories.FacilityRepository;
import application.backend.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Override
    public List<Facility> findAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public Facility createFacility(FacilityDTO facilityDTO) {
        Facility facility = new Facility();

        facility.setCompany(facilityDTO.getCompany());
        facility.setCountry(facilityDTO.getCountry());
        facility.setCity(facilityDTO.getCity());
        facility.setZipCode(facilityDTO.getZipCode());
        facility.setAddress(facilityDTO.getAddress());
        facility.setPhoneNumber(facilityDTO.getPhoneNumber());
        facility.setEmail(facilityDTO.getEmail());
        facility.setCalendarLink(facilityDTO.getCalendarLink());
        facility.setFacilityType(facilityDTO.getFacilityType());

        facilityRepository.save(facility);

        return facility;
    }

    @Override
    public Facility updateFacility(FacilityDTO facilityDTO) {
        Facility updatedFacility = facilityRepository.findById(facilityDTO.getId()).get();

        if (facilityDTO.getCompany() != null) {
            updatedFacility.setCompany(facilityDTO.getCompany());
        }
        if (facilityDTO.getCountry() != null) {
            updatedFacility.setCountry(facilityDTO.getCountry());
        }
        if (facilityDTO.getCity() != null) {
            updatedFacility.setCity(facilityDTO.getCity());
        }
        if (facilityDTO.getZipCode() != null) {
            updatedFacility.setZipCode(facilityDTO.getZipCode());
        }
        if (facilityDTO.getAddress() != null) {
            updatedFacility.setAddress(facilityDTO.getAddress());
        }
        if (facilityDTO.getPhoneNumber() != null) {
            updatedFacility.setPhoneNumber(facilityDTO.getPhoneNumber());
        }
        if (facilityDTO.getEmail() != null) {
            updatedFacility.setEmail(facilityDTO.getEmail());
        }
        if (facilityDTO.getCalendarLink() != null) {
            updatedFacility.setCalendarLink(facilityDTO.getCalendarLink());
        }
        if (updatedFacility.getFacilityType().equals(facilityDTO.getFacilityType())) {
            updatedFacility.setFacilityType(facilityDTO.getFacilityType());
        }

        facilityRepository.updateFacility(

                updatedFacility.getCountry(),
                updatedFacility.getCity(),
                updatedFacility.getZipCode(),
                updatedFacility.getAddress(),
                updatedFacility.getPhoneNumber(),
                updatedFacility.getEmail(),
                updatedFacility.getCalendarLink(),
                updatedFacility.getFacilityType(),
                updatedFacility.getId()
        );

        return updatedFacility;
    }

    @Override
    public Facility findFacilityById(long id) {
        return facilityRepository.findById(id);
    }

    @Override
    public void deleteFacilityById(long id) {
        facilityRepository.deleteById(id);
    }

}
