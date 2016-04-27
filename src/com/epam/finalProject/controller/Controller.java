package com.epam.finalProject.controller;

import com.epam.finalProject.resource.ConfigurationManager;
import com.epam.finalProject.command.ActionCommand;
import com.epam.finalProject.command.factory.ActionFactory;
import com.epam.finalProject.util.SessionRequestContent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.apache.log4j.PropertyConfigurator;


/**
 * Class-controller, extending {@link HttpServlet}.
 * <p>
 * Organizes work with incoming information from .jsp pages.
 * </p>
 * 
 * @author Kate
 *
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ActionFactory client;

	public void init() {
		client = new ActionFactory();
//		PropertyConfigurator.configure(ConfigurationManager.getProperty("log4j.path"));
	}

	/**
     * Gets information from .jsp pages, passes information to class, implementing patters command, gets processed 
     * information and link to the needed page, then sends received information to the .jsp page.
     * 
     * @throws ServletException
     * @throws IOException
     */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		SessionRequestContent sessionRequestContent = new SessionRequestContent();
		sessionRequestContent.extraction(request);
		ActionCommand command = client.defineCommand(sessionRequestContent);
		page = command.execute(sessionRequestContent);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			request = sessionRequestContent.updateRequest(request);
			dispatcher.forward(request, response);
		} else {
			page = ConfigurationManager.getProperty("path.page.error");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			request = sessionRequestContent.updateRequest(request);
			dispatcher.forward(request, response);
		}
	}	
}
