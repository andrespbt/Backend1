package main.com.dh.integrador.dao;


import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    public T save(T t) throws SQLException;
    public void delete(Long id) throws SQLException;
    public T search(Long id) throws SQLException;
    public List<T> searchAll() throws SQLException;
}
