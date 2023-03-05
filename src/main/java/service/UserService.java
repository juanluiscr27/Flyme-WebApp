package service;

import model.User;
import repository.UserRepository;
import util.PasswordEncoder;

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
}
