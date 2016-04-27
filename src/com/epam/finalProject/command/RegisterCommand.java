package com.epam.finalProject.command;

import com.epam.finalProject.exception.DAOException;
import com.epam.finalProject.service.RegistrationService;
import com.epam.finalProject.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import com.epam.finalProject.exception.PoolException;
import com.epam.finalProject.resource.MessageManager;
import com.epam.finalProject.util.SessionRequestContent;

public class RegisterCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_PASSWORDREPEAT = "passwordRepeat";
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public String execute(SessionRequestContent sessionRequestContent) {
		String page = null;
		String login = sessionRequestContent.getParameter(PARAM_NAME_LOGIN);
		String pass = sessionRequestContent.getParameter(PARAM_NAME_PASSWORD);
		String passRepeat = sessionRequestContent.getParameter(PARAM_NAME_PASSWORDREPEAT);
		try {
			if (login == "" || pass == "" || passRepeat == "") {
				sessionRequestContent.setAttribute("errorRegistrationMessage",
						MessageManager.getProperty("message.registrationError"));
				page = ConfigurationManager.getProperty("path.page.registration");
			} else {
				if (RegistrationService.checkInfo(login, pass, passRepeat) &&
						RegistrationService.addUser(login, pass)) {
					sessionRequestContent.setAttribute("user", login);
					page = RegistrationService.getPage();
				} else {
					sessionRequestContent.setAttribute("errorRegistrationMessage",
							MessageManager.getProperty("message.registrationError"));
					page = ConfigurationManager.getProperty("path.page.registration");
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
