package com.epam.finalProject.command.factory;

import com.epam.finalProject.command.ActionCommand;
import com.epam.finalProject.command.CommandEnum;
import com.epam.finalProject.command.EmptyCommand;
import org.apache.log4j.Logger;

import com.epam.finalProject.util.SessionRequestContent;

/**
 * Class-factory for incoming commands.
 * <p>
 * A factory to create a required action.
 * </p>
 * 
 * @author Hedgehog
 *
 */

public class ActionFactory {
	
	private Logger logger = Logger.getLogger(this.getClass());
		
	/**
     * Defines the incoming command and creates an action.
     * 
     * @param sessionRequestContent  SessionRequestContent object holding all session information
     */
	public ActionCommand defineCommand(SessionRequestContent sessionRequestContent) {
		ActionCommand current = new EmptyCommand();
		String action = sessionRequestContent.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
		return current;
	}
}
