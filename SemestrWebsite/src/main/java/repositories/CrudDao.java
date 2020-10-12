package repositories;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T,ID> {
    void save(T model) throws SQLException;
    void update(T model);
    void delete(ID id);
    Optional<T> find(ID id);
    List<T> findAll();


}