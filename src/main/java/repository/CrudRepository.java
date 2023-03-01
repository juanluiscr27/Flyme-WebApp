package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T add(T t);

    Optional<T> find(ID id);

    List<T> findAll();

    T update(T t);

    void delete(T t);
}
