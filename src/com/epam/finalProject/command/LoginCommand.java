package com.epam.finalProject.command;

import com.epam.finalProject.exception.DAOException;
import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.exception.PoolException;
import com.epam.finalProject.service.LoginService;
import com.epam.finalProject.resource.MessageManager;
import com.epam.finalProject.util.SessionRequestContent;
import org.apache.log4j.Logger;

public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public String execute(SessionRequestContent sessionRequestContent) {
		String page = null;
		String login = sessionRequestContent.getParameter(PARAM_NAME_LOGIN);
		String pass = sessionRequestContent.getParameter(PARAM_NAME_PASSWORD);
		try {
			if (login == "" || pass == "") {
				sessionRequestContent.setAttribute("errorLoginPassMessage",
						MessageManager.getProperty("message.loginerror"));
				page = ConfigurationManager.getProperty("path.page.login");
			} else {
				if (LoginService.checkLogin(login, pass)) {
					sessionRequestContent.setSessionAttribute("user", login);
					page = LoginService.getPage();
				} else {
					sessionRequestContent.setAttribute("errorLoginPassMessage",
							MessageManager.getProperty("message.loginerror"));
					page = ConfigurationManager.getProperty("path.page.login");			
				}
			}
		} catch (DAOException e) {
			page = ConfigurationManager.getProperty("path.page.error");	
			sessionRequestContent.setAttribute("error", e.getMessage());
			sessionRequestContent.setAttribute("property", e.getPropertyMessage());
			logger.error(e.getMessage());
		} catch (PoolException e) {
			page = ConfigurationManager.getProperty("path.page.error");	
			sessionRequestContent.setAttribute("error", e.getMessage());
			sessionRequestContent.setAttribute("property", e.getPropertyMessage());
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		return page;
	}
}
