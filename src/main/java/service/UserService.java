package service;

import model.User;
import repository.UserRepository;
import util.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(User registrationRequest) {
        // TODO: validate email address here
        
        String encodedPassword = PasswordEncoder.encodePassword(registrationRequest.getPassword());
        registrationRequest.setPassword(encodedPassword);
        return userRepo.add(registrationRequest);
    }
}
