package com.epam.finalProject.exception;

public class PoolException extends Exception {
	
	private Exception hidden;
    private String propertyMessage;

    public PoolException(String error) {
        super(error);
    }

    public PoolException(String error, Exception exception) {
        super(error);
        hidden = exception;
    }

    public Exception getHiddenException() {
        return this.hidden;
    }

    public String getPropertyMessage() {
        return propertyMessage;
    }

    public void setPropertyMessage(String propertyMessage) {
        this.propertyMessage = propertyMessage;
    }	
}
