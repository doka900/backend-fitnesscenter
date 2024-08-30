package application.backend.services;

import application.backend.models.DTO.CompanyDTO;
import application.backend.models.entities.Company;

import java.util.List;

public interface CompanyService {

    public Company createUser(CompanyDTO companyDTO);

    public Company updateUser(CompanyDTO companyDTO);

}
