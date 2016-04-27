package com.epam.finalProject.dao.impl;

import com.epam.finalProject.bean.User;
import com.epam.finalProject.dao.AbstractDAO;
import com.epam.finalProject.db.pool.DataSource;
import com.epam.finalProject.exception.DAOException;
import com.epam.finalProject.exception.PoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserDAO extends AbstractDAO<User> {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("properties.userRequest");
	
	private static final String SQL_SELECT_USER_BY_LOGIN = resource.getString("select.user.login");
	
	private static final String SQL_CHECK_USER = resource.getString("check.user");
	
	private static final String SQL_INSERT_NEW_USER = resource.getString("insert.user");
	
	private static final String SQL_SELECT_ID_BY_LOGIN = resource.getString("select.id.login");

	private Connection connection;

	public UserDAO() throws PoolException, ClassNotFoundException {
		super();
	}

	public User read(String login) throws DAOException, PoolException {
        PreparedStatement ps = null;
        User userObject = new User();
        try {
			connection = DataSource.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                userObject.setLogin(login);
                userObject.setPassword(resultSet.getString("password"));
                userObject.setRole(resultSet.getInt("role_id"));
            }
        } catch (SQLException e) {
        	DAOException dao = new DAOException(e.getMessage());
            dao.setPropertyMessage("error.dao.sqlexception");
            throw dao;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			connection = dataSource.returnConnection(connection);
		}
        return userObject;
    }
	
	public boolean check(String login) throws DAOException, PoolException {
		PreparedStatement ps = null;
		try {
			connection = DataSource.getConnection();
            ps = connection.prepareStatement(SQL_CHECK_USER);
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
            	return true;
            }
        } catch (SQLException e) {
        	DAOException dao = new DAOException(e.getMessage());
            dao.setPropertyMessage("error.dao.sqlexception");
            throw dao;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DataSource.returnConnection(connection);
			return true;
		}
	}
	
	public boolean add(String login, String pass) throws DAOException, PoolException {
		boolean result = true;
        PreparedStatement ps = null;
        try {
			connection = DataSource.getConnection();
            ps = connection.prepareStatement(SQL_INSERT_NEW_USER);
            ps.setString(1, login);
            ps.setString(2, pass);
            ps.setInt(3, 3);
            int count = 0;
            count = ps.executeUpdate();
            if (count == 0) {
                result = false;
            }
        } catch (SQLException e) {  
        	DAOException dao = new DAOException(e.getMessage());
            dao.setPropertyMessage("error.dao.sqlexception");
            throw dao;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DataSource.returnConnection(connection);
		}
        return result;
	}
	
	public int fetchUser(String name) throws DAOException, PoolException {
		PreparedStatement ps = null;
        try {
			connection = DataSource.getConnection();
            ps = connection.prepareStatement(SQL_SELECT_ID_BY_LOGIN);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
            	return 0;
            }
        } catch (SQLException e) {
        	DAOException dao = new DAOException(e.getMessage());
            dao.setPropertyMessage("error.dao.sqlexception");
            throw dao;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DataSource.returnConnection(connection);
		}
		return 0;
	}
	
	@Override
	public User read(int id) {
		return null;
	}

}
