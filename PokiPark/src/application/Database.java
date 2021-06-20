package application;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import POJO.*;

public class Database {
	
	final static String dbUrl = "jdbc:mysql://nic-we.de:3306/pokipark";
	final static String dbUsername = "pokipark";
	final static String dbPassword = "pummeluff654";
	
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
	
	static ArrayList<User> userlist = new ArrayList<User>();
	static ArrayList<Poki> pokilist = new ArrayList<Poki>();
	
	public static void initData(String database) throws SQLException {
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM " + database);
		
		if(database.equals("userbank")) {
			userlist.clear();
			while(resultSet.next()) {
				User u = new User();
				u.setId(resultSet.getInt("id"));
				u.setUsername(resultSet.getString("username"));
				u.setPassword(resultSet.getString("password"));
				u.setEmail(resultSet.getString("email"));
				u.setAdmin(resultSet.getInt("admin"));
				userlist.add(u);
			}
		} else {
			pokilist.clear();
			while(resultSet.next()) {
				Poki p = new Poki();
				p.setId(resultSet.getInt("id"));
				p.setName(resultSet.getString("name"));
				p.setTyp(resultSet.getString("typ"));
				p.setSex(resultSet.getString("sex"));
				p.setImg_path(resultSet.getString("img_path"));
				p.setPrevEvo(resultSet.getString("prevEvo"));
				p.setNextEvo(resultSet.getString("nextEvo"));
				p.setPokedexInfo(resultSet.getString("pokedexInfo"));
				pokilist.add(p);
			}
		}
		closeConnection();
	}
	
	private static void closeConnection() {
		if(resultSet != null) {try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
		if(statement != null) {try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
		if(connection != null) {try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}}
	}
	
	public static void addUser(String username, String password, String email) throws SQLException {
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		statement = connection.createStatement();
		
		userlist.forEach((item) -> {
			if (usernameIsValid(item.getUsername(), username) & emailIsValid(item.getEmail(), email)) {
				String sqlCommand = "INSERT INTO userbank (username, password, email, id, admin) VALUES ('"
						+ username + "', '" + password + "', '" + email + "', NULL,  0)";
				try {
					statement.executeUpdate(sqlCommand);
				} catch (SQLException e) {
					e.printStackTrace();
				}}
			});
		closeConnection();
	}
	
	public static boolean usernameIsValid(String usernameItem, String username) {
		if(username.equals(usernameItem)) {
			return false;
		}
		return true;
	}
	
	public static boolean emailIsValid(String emailItem, String email) {
		Pattern at = Pattern.compile("[@]");
		Pattern dot = Pattern.compile("[.]");
		Matcher hasAt = at.matcher(email);
		Matcher hasDot = dot.matcher(email);
		
		if(email.equals(emailItem)) {
			return false;
		} else if(hasAt.find() & hasDot.find()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Poki getPoki(String name, String sex) throws SQLException {
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		statement = connection.createStatement();
		resultSet = statement.executeQuery("SELECT `name` FROM `pokibank` WHERE name = '" + name + "'");
		
		Poki p = new Poki();
		p.setId(resultSet.getInt("id"));
		p.setName(resultSet.getString("name"));
		p.setTyp(resultSet.getString("typ"));
		p.setSex(sex);
		p.setImg_path(resultSet.getString("img_path"));
		p.setPrevEvo(resultSet.getString("prevEvo"));
		p.setNextEvo(resultSet.getString("nextEvo"));
		p.setPokedexInfo(resultSet.getString("pokedexInfo"));
		pokilist.add(p);
		
		closeConnection();
		return p;
	}
	
	public static void removePoki(int id) {
		pokilist.forEach((item) -> {
			if(id == item.getId()) {
				pokilist.remove(item);
			}
		});
	}
	
	public static ArrayList<User> getUserlist() {
		return userlist;
	}
}
