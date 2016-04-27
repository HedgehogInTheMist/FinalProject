package com.epam.finalProject.bean;

public class User extends Entity {
	
	private String login;
    private String password;
    private String name;
    private String surname;
    private int roleId;

    public User() {
        super();
        this.login = "";
        this.password = "";
        this.name = "";
        this.surname = "";
        this.roleId = 0;
    }

    public User(int id, String login, String password, String name, String surname, int role) {
        super(id);
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = role;
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getRole() {
		return roleId;
	}

	public void setRole(int role) {
		this.roleId = role;
	}
}
