package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    T add(T t) throws SQLException;

    Optional<T> find(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    T update(T t) throws  SQLException;

    void delete(T t) throws SQLException;
}
