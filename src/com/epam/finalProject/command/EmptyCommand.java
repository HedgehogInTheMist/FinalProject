package com.epam.finalProject.command;

import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.util.SessionRequestContent;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(SessionRequestContent sessionRequestContent) {
		String page = ConfigurationManager.getProperty("path.page.error");
		return page;
	}
}
