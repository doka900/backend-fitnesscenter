package application.backend.repositories;

import application.backend.models.entities.Facility;
import application.backend.models.enums.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    Facility findById(long id);

    List<Facility> findAll();

    @Modifying
    @Transactional
    @Query(value = "UPDATE facilities f SET f.country = ?1, f.city = ?2, f.zip_code = ?3, f.address = ?4, " +
            "f.phone_number = ?5, f.e_mail = ?6, f.calendar_link = ?7, f.facility_type = ?8 " +
            "WHERE f.id = ?9", nativeQuery = true)
    void updateFacility(String country, String city, String zipCode,
                        String address, String phoneNumber, String email,
                        String calendarLink, String facility_type, long id);

}
