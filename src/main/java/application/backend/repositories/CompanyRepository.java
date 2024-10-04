package application.backend.repositories;

import application.backend.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);


    @Modifying
    @Transactional
    @Query(value = "update company c set c.name = ?1, c.description = ?2, c.slogan = ?3, c.logo_image = ?4 where c.id = ?5", nativeQuery = true)
    void updateCompany(String name, String description, String slogan, byte[] logo_image, long id);


    Company findByName(String name);

}
