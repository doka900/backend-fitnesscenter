package application.backend.services;

import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.entities.LoyaltyCard;

public interface LoyaltyCardService {

    public LoyaltyCard findById(Long id);
    public LoyaltyCard findByUserId(Long id);
    public LoyaltyCard save(LoyaltyCardDTO loyaltyCardDTO);
    public void deleteById(Long id);

    public LoyaltyCard updateLoyaltyCard(LoyaltyCardDTO loyaltyCardDTO);
}
