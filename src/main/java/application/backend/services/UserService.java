package application.backend.services;

import java.util.List;
import application.backend.models.DTO.UserDTO;
import application.backend.models.entities.User;

public interface UserService {

    public List<User> findAllUsers();

    public User createUser(UserDTO userDTO);

    public User updateUser(UserDTO updateUserDTO, String username);

    public User findUserById(long id);

    public User findByUsername(String username);

    public User changePassword(String newPassword, String username);

    public Boolean oldPasswordVerification(String oldPassword, String username);

    public User findUserByUsername(String username);

}
