package application.backend.controllers;

import application.backend.models.DTO.CompanyDTO;
import application.backend.models.entities.Company;
import application.backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Company>> findAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Company> saveCompany(@RequestBody CompanyDTO companyDTO) {
        Company newCompany = companyService.createCompany(companyDTO);
        return new ResponseEntity<Company>(newCompany, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{name}/")
    public ResponseEntity<Company> findCompanyByName(@PathVariable("name") String name) {
        Company company = companyService.findCompanyByName(name);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @PutMapping(value = "/update/", consumes = "application/json")
    public ResponseEntity<Company> updateCompany(@RequestBody CompanyDTO companyDTO) {
        return new ResponseEntity<Company>(companyService.updateCompany(companyDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return new ResponseEntity<String>("Company " + id +" was deleted successfully",HttpStatus.OK);
    }
}
