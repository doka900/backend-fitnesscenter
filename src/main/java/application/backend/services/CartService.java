package application.backend.services;

import application.backend.models.DTO.CartDTO;
import application.backend.models.entities.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> findAllCarts();
    public Cart findCartById(Long id);
    public Cart createCart(CartDTO cartDTO);
    public Cart updateCart(CartDTO cartDTO);
    public void deleteCart(Cart cart);
}
