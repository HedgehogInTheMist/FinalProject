package com.epam.finalProject.dao;

import com.epam.finalProject.bean.Entity;
import com.epam.finalProject.db.pool.DataSource;
import com.epam.finalProject.exception.PoolException;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T extends Entity> {
	
	protected Connection connection;
    protected DataSource dataSource;
	
	public AbstractDAO() throws PoolException, ClassNotFoundException {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            PoolException pool = new PoolException(e.getMessage());
            pool.setPropertyMessage("error.pool.exception");
            throw pool;
        }        
    }
	
	public abstract T read(int id);
}
