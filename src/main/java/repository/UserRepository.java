package repository;

import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User add(User user);

    Optional<User> find(Long id);

    List<User> findAll();

    User update(User user);

    void delete(User user);

    Optional<User> findByEmail(String email);

    List<String> findAllEmails();
}
