package com.epam.finalProject.command;

import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.util.SessionRequestContent;

public class MainClientCommand implements ActionCommand {

	@Override
	public String execute(SessionRequestContent sessionRequestContent) {
		return ConfigurationManager.getProperty("path.page.main.client");
	}
}
