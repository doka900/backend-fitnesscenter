package application.backend.controllers;

import application.backend.models.DTO.JwtAuthRequestDTO;
import application.backend.models.DTO.UserDTO;
import application.backend.models.DTO.UserTokenDTO;
import application.backend.models.entities.User;
import application.backend.repositories.UserRepository;
import application.backend.repositories.VerifiedTokenRepository;
import application.backend.security.TokenUtils;
import application.backend.security.VerifiedToken;
import application.backend.services.EmailService;
import application.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private CompanyController companyController;

    @Autowired
    private VerifiedTokenRepository verifiedTokenRepository;

    @Autowired
    private EmailService emailService;

    @PutMapping(value = "/{username}/", consumes = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO updateUserDTO,
                                           @PathVariable("username") String username) {
        return new ResponseEntity<User>(userService.updateUser(updateUserDTO, username), HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/register/", consumes = "application/json")
    public ResponseEntity<User> Register(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);


        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<User> findUserById(@PathVariable("id") long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "username/{username}/")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login/")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequestDTO authenticationRequest,
                                                       HttpServletResponse response) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userService.findUserByUsername(userDetails.getUsername());
        if (!user.isVerified()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please verify your email before logging in.");
        }


        String role = getUserRole(user.getUsername());

        String jwt = tokenUtils.generateToken(userDetails, role);
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenDTO(jwt, expiresIn));
    }

    private String getUserRole(String username) {
        User user = userService.findByUsername(username);
        return user.getRole().name();
    }

    @PutMapping(value = "changePassword/{username}/")
    public ResponseEntity<User> updateUser(@RequestBody String newPassword, @PathVariable("username") String username) {
        return new ResponseEntity<>(userService.changePassword(newPassword, username), HttpStatus.OK);
    }

    @PostMapping(value = "oldPasswordVerification/{username}/")
    public ResponseEntity<Boolean> checkOldPassword(@RequestBody String password,
                                                    @PathVariable("username") String username) {
        return new ResponseEntity<Boolean>(userService.oldPasswordVerification(password, username), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<String>("User " + id + " was deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/verify-email/")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        VerifiedToken verifiedToken = verifiedTokenRepository.findByToken(token);
        if (verifiedToken == null) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        User user = verifiedToken.getUser();
        if (verifiedToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired.");
        }

        user.setVerified(true);
        userRepository.save(user);
        return ResponseEntity.ok("Email verified successfully.");
    }
    @GetMapping("/checkUsername/")
    public ResponseEntity<?> checkUsernameExists(@RequestParam String username) {
        return ResponseEntity.ok(userService.usernameExists(username));
    }

    @GetMapping("/checkEmail/")
    public ResponseEntity<?> checkEmailExists(@RequestParam String email) {
        return ResponseEntity.ok(userService.emailExists(email));
    }
}