package com.epam.finalProject.service;

import com.epam.finalProject.dao.impl.UserDAO;
import com.epam.finalProject.exception.DAOException;
import com.epam.finalProject.exception.PoolException;

public class UserService {

	public static int fetchUser(String name) throws DAOException, PoolException, ClassNotFoundException {
		UserDAO userDao = new UserDAO();
		return userDao.fetchUser(name);
	}
}
