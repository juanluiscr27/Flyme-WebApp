package service;

import model.User;
import repository.UserRepository;
import util.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(User registrationRequest) {
        String encodedPassword = PasswordEncoder.encodePassword(registrationRequest.getPassword());
        registrationRequest.setPassword(encodedPassword);
        return userRepo.add(registrationRequest);
    }
    public User login(String email, String password) throws IllegalArgumentException {
        Optional<User> unvalidatedUser = userRepo.findByEmail(email);
        User verifiedUser = unvalidatedUser.orElseThrow(() -> new IllegalArgumentException("User not found"));
        String encodedPassword = PasswordEncoder.encodePassword(password);
        if (!encodedPassword.equals(verifiedUser.getPassword()))
            throw new IllegalArgumentException("Incorrect password");
        return verifiedUser;
    }
    public User find(String email) throws IllegalArgumentException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        return optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    public User update(User user) {
        String encodedPassword = PasswordEncoder.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.update(user);
    }
    public List<String> findAllEmails(String startsWith) {
        return new ArrayList<>(userRepo.findAllEmails(startsWith));
    }
    public void delete(User user) {
        userRepo.delete(user);
    }
}
