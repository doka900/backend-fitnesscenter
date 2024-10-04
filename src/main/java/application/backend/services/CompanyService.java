package application.backend.services;

import application.backend.models.DTO.CompanyDTO;
import application.backend.models.entities.Company;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {

    public Company createCompany(CompanyDTO companyDTO);

    public Company updateCompany(CompanyDTO companyDTO);

    public List<Company> getAllCompanies();

    public Company findCompanyByName(String name);

    public void deleteById(Long id);
}
