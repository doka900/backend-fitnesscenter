package application.backend.services.impl;


import application.backend.models.DTO.UserDTO;
import application.backend.models.entities.Cart;
import application.backend.models.entities.LoyaltyCard;
import application.backend.models.entities.Program;
import application.backend.models.entities.User;
import application.backend.models.enums.Roles;
import application.backend.repositories.CartRepository;
import application.backend.repositories.LoyaltyCardRepository;
import application.backend.repositories.UserRepository;
import application.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserDTO userDTO) {

        User user = new User();
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        loyaltyCard.setDiscount(false);
        loyaltyCard.setPoints(0);
        Cart cart = new Cart();

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setRegistrationDate(LocalDate.now());
        user.setDescription(userDTO.getDescription());
        user.setDisplayName(userDTO.getDisplay_name());
        user.setProfileImage(userDTO.getProfileImage());
        user.setRole(Roles.USER);
        user.setLoyaltyCard(loyaltyCard);
        user.setCart(cart);
        userRepository.save(user);
        loyaltyCardRepository.save(loyaltyCard);
        cartRepository.save(cart);

        return user;
    }


    @Override
    public User updateUser(UserDTO updateUserDTO, String username) {

        User updatedUser = userRepository.findByUsername(username);

        if (updateUserDTO.getEmail() != null) {
            updatedUser.setEmail(updateUserDTO.getEmail());
        }

        if (updateUserDTO.getDateOfBirth() != null) {
            updatedUser.setDateOfBirth(updateUserDTO.getDateOfBirth());
        }
        if (updateUserDTO.getName() != null) {
            updatedUser.setName(updateUserDTO.getName());
        }
        if (updateUserDTO.getSurname() != null) {
            updatedUser.setSurname(updateUserDTO.getSurname());
        }

        if (updateUserDTO.getDescription() != null) {
            updatedUser.setDescription(updateUserDTO.getDescription());
        }

        if (updateUserDTO.getDisplay_name() != null) {
            updatedUser.setDisplayName(updateUserDTO.getDisplay_name());
        }

        if (updateUserDTO.getProfileImage() != null) {
            updatedUser.setProfileImage(updateUserDTO.getProfileImage());
        }

        userRepository.updateUser(updatedUser.getEmail(), updatedUser.getDateOfBirth(), updatedUser.getName(), updatedUser.getSurname(), updatedUser.getDescription(), updatedUser.getDisplayName(), updatedUser.getProfileImage(), updatedUser.getId());

        return updatedUser;
    }


    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User changePassword(String newPassword, String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.changePassword(user.getPassword(), user.getId());

        return user;
    }

    @Override
    public Boolean oldPasswordVerification(String oldPassword, String username) {
        User user = userRepository.findByUsername(username);
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
