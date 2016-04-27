package com.epam.finalProject.service;

import com.epam.finalProject.bean.User;
import com.epam.finalProject.dao.impl.UserDAO;
import com.epam.finalProject.exception.DAOException;
import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.exception.PoolException;

public class LoginService {
	
	private static String page;
	
	public static boolean checkLogin(String enterLogin, String enteredPass) throws DAOException, PoolException, ClassNotFoundException {
		UserDAO userdao = new UserDAO();
		User user = userdao.read(enterLogin);
        switch (user.getRole()) {
		case 1:
			page = ConfigurationManager.getProperty("path.page.main.admin");
			break;
		case 2:
			page = ConfigurationManager.getProperty("path.page.main.user");
			break;
		default:
			break;
		}
        return user.getPassword().equals(enteredPass);
	}
	
	public static String getPage() {
		return page;
	}
}
