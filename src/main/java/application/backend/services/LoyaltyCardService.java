package application.backend.services;

import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.entities.LoyaltyCard;
import application.backend.repositories.LoyaltyCardRepository;

public interface LoyaltyCardService {

    public LoyaltyCard findById(LoyaltyCardDTO loyaltyCardDTO);
    public LoyaltyCard save(LoyaltyCardDTO loyaltyCardDTO);
    public void deleteById(LoyaltyCardDTO loyaltyCardDTO);
}
