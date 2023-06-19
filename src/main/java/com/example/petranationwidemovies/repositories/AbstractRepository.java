package com.example.petranationwidemovies.repositories;

import com.example.petranationwidemovies.dao.Dao;
import com.example.petranationwidemovies.database.DatabaseConnection;

import java.sql.Connection;

public abstract class AbstractRepository implements Dao {
    protected final Connection conn = DatabaseConnection.getInstance();

}
