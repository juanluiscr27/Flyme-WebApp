package repository;

import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<String> findAllEmails();
}
