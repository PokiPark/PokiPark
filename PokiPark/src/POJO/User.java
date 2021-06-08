package POJO;

import java.sql.*;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	
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
	
	
}
