package application.backend.repositories;

import application.backend.models.entities.Facility;
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
    @Query(value = "UPDATE facilities f SET f.name =?1 ,f.country = ?2, f.city = ?3, f.zip_code = ?4, f.address = ?5, " +
            "f.phone_number = ?6, f.e_mail = ?7, f.calendar_link = ?8, f.facility_type = ?9," +
            "f.image = ?10 WHERE f.id = ?11", nativeQuery = true)
    void updateFacility(String name, String country, String city, String zipCode,
                        String address, String phoneNumber, String email,
                        String calendarLink, String facility_type, String image, long id);

}
