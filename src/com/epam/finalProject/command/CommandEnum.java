package com.epam.finalProject.command;

public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	
	REGISTER {
		{
			this.command = new RegisterCommand();
		}
	};
	
	ActionCommand command;
	public ActionCommand getCurrentCommand() {
		return command;
	}
}
