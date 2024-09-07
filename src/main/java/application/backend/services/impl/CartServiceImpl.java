package application.backend.services.impl;

import application.backend.models.DTO.CartDTO;
import application.backend.models.entities.Cart;
import application.backend.repositories.CartRepository;
import application.backend.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findCartById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart createCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUser(cartDTO.getUser());
        cart.setPrograms(cartDTO.getUser().getPrograms());

        cartRepository.save(cart);

        return cart;
    }

    @Override
    public Cart updateCart(CartDTO cartDTO) {
        return null;
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
