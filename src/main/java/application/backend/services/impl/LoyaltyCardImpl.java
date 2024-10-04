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

    public LoyaltyCard findById(Long id) {
        return loyaltyCardRepository.findById(id).orElse(null);
    }

    public LoyaltyCard findByUserId(Long id) {
        return loyaltyCardRepository.findByUserId(id);
    }

    @Override
    public LoyaltyCard save(LoyaltyCardDTO loyaltyCardDTO) {

        LoyaltyCard loyaltyCard = new LoyaltyCard();

        loyaltyCard.setPoints(loyaltyCardDTO.getPoints());
        loyaltyCard.setDiscount(false);

        loyaltyCardRepository.save(loyaltyCard);

        return loyaltyCard;
    }

    public void deleteById(Long id) {
        loyaltyCardRepository.deleteById(id);
    }

    @Override
    public LoyaltyCard updateLoyaltyCard(LoyaltyCardDTO loyaltyCardDTO) {
        return null;
    }


}
