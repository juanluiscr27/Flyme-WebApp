package service;

import model.User;
import model.UserDTO;
import repository.UserRepository;
import util.PasswordEncoder;
import util.Validator;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserDTO register(User registrationRequest) {
        if (!Validator.isValidEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email address is not in a valid format");
        }

        String encodedPassword = PasswordEncoder.encodePassword(registrationRequest.getPassword());
        registrationRequest.setPassword(encodedPassword);
        return userRepo.add(registrationRequest);
    }
    public UserDTO login(String email, String password) throws IllegalArgumentException {
        Optional<UserDTO> unvalidatedUser = userRepo.findByEmail(email);
        UserDTO verifiedUser = unvalidatedUser.orElseThrow(() -> new IllegalArgumentException("User not found"));

        String encodedPassword = PasswordEncoder.encodePassword(password);
        if (!encodedPassword.equals(verifiedUser.password()))
            throw new IllegalArgumentException("Incorrect password");
        return verifiedUser;
    }
    public UserDTO find(String email) throws IllegalArgumentException {
        Optional<UserDTO> optionalUser = userRepo.findByEmail(email);
        return optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    public UserDTO update(User user) {
        return userRepo.update(user);
    }
    public UserDTO updatePassword(User user) {
        String encodedPassword = PasswordEncoder.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepo.update(user);
    }
    public boolean isEmailAvailable(String email) {
        return !userRepo.isEmailPresent(email);
    }
    public void delete(UserDTO user) {
        userRepo.delete(user);
    }
}
