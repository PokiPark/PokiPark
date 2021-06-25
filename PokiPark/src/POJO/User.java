package POJO;

import application.Database;

public class User {

	private int id;
	private String username, password, email;
	private boolean isAdmin;

	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ");
		sb.append(getId());
		sb.append("\t");
		sb.append(getUsername());
		sb.append("\t");
		sb.append(getPassword());
		sb.append("\t");
		sb.append(getEmail());
		sb.append("\t");
		sb.append(isAdmin());
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActiveUser() {
		Database.setActiveUser(this);
	}

	public void setAdmin(int isAdmin) {
		if (isAdmin == 0)
			this.isAdmin = false;
		else if (isAdmin == 1)
			this.isAdmin = true;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}