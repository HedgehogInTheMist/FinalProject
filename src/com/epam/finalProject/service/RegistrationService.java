package com.epam.finalProject.service;

import com.epam.finalProject.exception.DAOException;
import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.dao.impl.UserDAO;
import com.epam.finalProject.exception.PoolException;

public class RegistrationService {
	
	private static String page;
	
	public static boolean checkInfo(String login, String pass, String passRepeat) throws DAOException,
			PoolException, ClassNotFoundException {
		UserDAO userdao = new UserDAO();
		if (userdao.check(login) && pass.equals(passRepeat)) {
			page = ConfigurationManager.getProperty("path.page.main.user");
			return true;
		} else {
			return false;		
		}
	}

	public static boolean addUser(String login, String pass) throws DAOException, PoolException, ClassNotFoundException {
		UserDAO userdao = new UserDAO();
		if (userdao.add(login, pass)) {
			return true;
		} else {
			return false;
		}			
	}

	public static String getPage() {
		return page;
	}
}
