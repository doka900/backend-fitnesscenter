package application.backend.services.impl;

import application.backend.models.DTO.CompanyDTO;
import application.backend.models.entities.Company;
import application.backend.repositories.CompanyRepository;
import application.backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company createUser(CompanyDTO companyDTO) {

        Company company = new Company();

        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setSlogan(companyDTO.getSlogan());
        company.setLogoImage(companyDTO.getLogoImage());

        companyRepository.save(company);

        return company;
    }

    @Override
    public Company updateUser(CompanyDTO companyDTO) {
        Company updatedCompany = companyRepository.findById(companyDTO.getId()).orElse(null);

       if(companyDTO.getName() != null) {
           updatedCompany.setName(companyDTO.getName());
       }

       if(companyDTO.getDescription() != null) {
           updatedCompany.setDescription(companyDTO.getDescription());
       }
       if(companyDTO.getSlogan() != null) {
           updatedCompany.setSlogan(companyDTO.getSlogan());

       }
       if(companyDTO.getLogoImage() != null) {
           updatedCompany.setLogoImage(companyDTO.getLogoImage());
       }

       companyRepository.updateCompany(
               updatedCompany.getName(),
               updatedCompany.getDescription(),
               updatedCompany.getSlogan(),
               updatedCompany.getLogoImage(),
               updatedCompany.getId()
       );

       return updatedCompany;
    }
}
