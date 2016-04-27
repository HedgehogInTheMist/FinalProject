package com.epam.finalProject.command;

import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.util.SessionRequestContent;

public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(SessionRequestContent sessionRequestContent) {
		sessionRequestContent.removeSession();
		return ConfigurationManager.getProperty("path.page.login");
	}
}
