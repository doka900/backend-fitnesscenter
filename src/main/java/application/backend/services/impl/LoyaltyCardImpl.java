package application.backend.services.impl;

import application.backend.models.DTO.LoyaltyCardDTO;
import application.backend.models.entities.LoyaltyCard;
import application.backend.repositories.LoyaltyCardRepository;
import application.backend.services.LoyaltyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyCardImpl implements LoyaltyCardService {
    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    public LoyaltyCard findById(LoyaltyCardDTO loyaltyCardDTO) {
        return loyaltyCardRepository.findById(loyaltyCardDTO.getId()).orElse(null);
    }

    @Override
    public LoyaltyCard save(LoyaltyCardDTO loyaltyCardDTO) {

        LoyaltyCard loyaltyCard = new LoyaltyCard();

        loyaltyCard.setPoints(loyaltyCardDTO.getPoints());
        loyaltyCard.setDiscount(false);

        loyaltyCardRepository.save(loyaltyCard);

        return loyaltyCard;
    }

    public void deleteById(LoyaltyCardDTO loyaltyCardDTO) {
        loyaltyCardRepository.deleteById(loyaltyCardDTO.getId());
    }


}
