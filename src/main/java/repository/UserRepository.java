package repository;

import model.User;
import model.UserDTO;

import java.util.Set;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserDTO add(User user);

    Optional<UserDTO> find(Long id);

    List<UserDTO> findAll();

    UserDTO update(User user);

    void delete(UserDTO user);

    Optional<UserDTO> findByEmail(String email);
    
    boolean isEmailPresent(String email);
}
