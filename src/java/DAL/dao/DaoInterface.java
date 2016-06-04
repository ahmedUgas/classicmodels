package DAL.dao;

import DAL.entities.EntityInterface;
import java.util.List;

public interface DaoInterface<T extends EntityInterface> {

    public List<T> select();

    public T select(int pk);

    public int insert(T entity);

    public int update(T entity);

    public int delete(int pk);

}// end of interface
