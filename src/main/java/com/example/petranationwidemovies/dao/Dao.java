package com.example.petranationwidemovies.dao;

import java.sql.SQLException;

public interface Dao {
    int add(Object element) throws SQLException;
    Object get() throws SQLException;
    int update(Object element) throws SQLException;
    Object delete(int id) throws SQLException;
}
