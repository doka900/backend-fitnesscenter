package application.backend.repositories;

import application.backend.models.entities.FacilitySpace;
import application.backend.models.enums.FacilitySpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FacilitySpaceRepository extends JpaRepository<FacilitySpace, Long> {


    @Query(value = "select * from facility_spaces fs where fs.facility_id = ?1", nativeQuery = true)
    List<FacilitySpace> findByFacilityId(Long facilityId);

    @Modifying
    @Transactional
    @Query(value = "update facility_spaces fs set fs.name = ?1, fs.capacity = ?2, fs.type = ?3 where fs.id=?4 ", nativeQuery = true)
    void updateFacilitySpace(String name, Long capacity, String facility_type, Long id);
}
