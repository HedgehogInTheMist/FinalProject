package com.epam.finalProject.command;

import com.epam.finalProject.util.SessionRequestContent;

public interface ActionCommand {

	String execute(SessionRequestContent sessionRequestContent);
}
