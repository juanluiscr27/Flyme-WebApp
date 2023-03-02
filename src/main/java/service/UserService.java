package service;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(User registrationRequest) {
        // TODO: validate email address here
        return userRepo.add(registrationRequest);
    }
}
