package com.epam.finalProject.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SessionRequestContent {
	
	private Map<String, String[]> parameterMap = new LinkedHashMap<String, String[]>();
	private Map<String, Object> attributeMap = new LinkedHashMap<String, Object>();
    private Map<String, Object> sessionMap = new LinkedHashMap<String, Object>();
	private HttpSession session = null;

	public void extraction(HttpServletRequest request) {
		String attributeName;
        String sessionAttributeName;
		session = request.getSession(true);
		Enumeration<String> sessionAttributeNames = session.getAttributeNames();
		while (sessionAttributeNames.hasMoreElements()) {
            sessionAttributeName = sessionAttributeNames.nextElement();
            sessionMap.put(sessionAttributeName, session.getAttribute(sessionAttributeName));
        }
		parameterMap.putAll(request.getParameterMap());
		Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            attributeName = attributeNames.nextElement();
            attributeMap.put(attributeName, request.getAttribute(attributeName));
        }		
	}
	
	public HttpServletRequest updateRequest(HttpServletRequest request) {
        String attributeName;
        String sessionAttributeName;
        HttpSession session = request.getSession(true);
        Set<String> attributeNames = attributeMap.keySet();
        Iterator<String> attributeNamesIterator = attributeNames.iterator();
        while (attributeNamesIterator.hasNext()) {
            attributeName = attributeNamesIterator.next();
            request.setAttribute(attributeName, attributeMap.get(attributeName));
        }
        Set<String> sessionAttributeNames = sessionMap.keySet();
        Iterator<String> sessionAttributeNamesIterator = sessionAttributeNames.iterator();
        while (sessionAttributeNamesIterator.hasNext()) {
            sessionAttributeName = sessionAttributeNamesIterator.next();
            session.setAttribute(sessionAttributeName, sessionMap.get(sessionAttributeName));
        }
        return request;
    }
	
	public String getParameter(String key) {
        return parameterMap.get(key)[0];
    }
	
	public void setAttribute(String key, Object value) {
        attributeMap.put(key, value);
    }
    
    public void setSessionAttribute(String key, Object value) {
        sessionMap.put(key, value);
    }
    
    public Object getSessionAttribute(String key) {
        return sessionMap.get(key);
    }
    
    public void removeSession() {
        this.session.invalidate();
    }
}
