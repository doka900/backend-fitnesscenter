package application.backend.models.DTO;

import application.backend.models.entities.Company;
import application.backend.models.enums.FacilityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDTO {

    private Long id;

    private Company company;

    private String country;


    private String city;


    private String zipCode;


    private String address;


    private String phoneNumber;


    private String email;


    private String calendarLink;

    private FacilityType facilityType;

    private String image;

}
