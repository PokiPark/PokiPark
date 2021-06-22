package application;

import java.sql.*;
import java.util.*;

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
	static ArrayList<PokedexPoki> pokedex = new ArrayList<PokedexPoki>();
	
	public static User activeUser;

	
	private static void openConnection() throws SQLException {
		connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		statement = connection.createStatement();
	}
	private static void closeConnection() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	public static void initData(String table) throws SQLException {
		openConnection();
		resultSet = statement.executeQuery("SELECT * FROM " + table);

		if (table.equals("usertable")) {
			userlist.clear();
			while (resultSet.next()) {
				User u = new User();
				u.setId(resultSet.getInt("id"));
				u.setUsername(resultSet.getString("username"));
				u.setPassword(resultSet.getString("password"));
				u.setEmail(resultSet.getString("email"));
				u.setAdmin(resultSet.getInt("admin"));
				userlist.add(u);
			}
		} else if (table.equals("pokitable")) {
			pokilist.clear();
			while (resultSet.next()) {
				Poki p = new Poki();
				p.setId(resultSet.getInt("id"));
				p.setName(resultSet.getString("name"));
				p.setTyp(resultSet.getString("typ"));
				p.setImg_path(resultSet.getString("img_path"));
				p.setAnzahl(resultSet.getInt("anzahl"));
				pokilist.add(p);
			}
		} else if (table.equals("pokedex")) {
			pokedex.clear();
			while (resultSet.next()) {
				PokedexPoki pp = new PokedexPoki();
				pp.setId(resultSet.getInt("id"));
				pp.setName(resultSet.getString("name"));
				pp.setTyp(resultSet.getString("typ"));
				pp.setImg_Path(resultSet.getString("img_path"));
				pp.setFirstEvo(resultSet.getString("firstEvo"));
				pp.setSecondEvo(resultSet.getString("secondEvo"));
				pp.setThirdEvo(resultSet.getString("thirdEvo"));
				pp.setPokedexEntry(resultSet.getString("pokedexEntry"));
				pokedex.add(pp);
			}
		}
		closeConnection();
	}
	
	public static void sendSqlCommand(String sqlCommand) throws SQLException {
		openConnection();
		statement.execute(sqlCommand);
		closeConnection();
	}

	public static void addToUserTable(String username, String password, String email) throws SQLException {
		openConnection();

		String sqlCommand = "INSERT INTO userbank (username, password, email, id, admin) VALUES ('" + username + "', '"
				+ password + "', '" + email + "', NULL,  0)";
		statement.execute(sqlCommand);

		closeConnection();
	}

	public static void addToPokiTable(String name, String typ, String img_path, int anzahl, int id)
			throws SQLException {
		openConnection();

		String sqlCommand = "INSERT INTO pokibank (name, typ, img_path, anzahl, id) VALUES ('" + name + "', '" + typ
				+ "', '" + img_path + "', '" + anzahl + "', '" + id + "')";
		statement.execute(sqlCommand);

		closeConnection();
	}

	public static void addToPokedexTable(String name, String typ, String img_path, String firstEvo, String secondEvo,
			String thirdEvo, String pokedexEntry, int id) throws SQLException {
		openConnection();

		String sqlCommand = "INSERT INTO pokedex (name, typ, img_path, firstEvo, secondEvo, thirdEvo, pokedexEntry, id) VALUES ('"
				+ name + "', '" + typ + "', '" + img_path + "', '" + firstEvo + "', '" + secondEvo + "', '" + thirdEvo
				+ "', '" + pokedexEntry + "', '" + id + "')";
		statement.execute(sqlCommand);

		closeConnection();
	}

	public static void removeFromTable(String table_name, int id) throws SQLException {
		openConnection();
		
		String sqlCommand = "DELETE FROM '" + table_name + "' WHERE '" + table_name + "'.'id' = " + id;
		statement.execute(sqlCommand);
		
		closeConnection();
	}

	public static ArrayList<PokedexPoki> getPokedex() {
		return pokedex;
	}

	public static ArrayList<Poki> getPokilist() {
		return pokilist;
	}

	public static ArrayList<User> getUserlist() {
		return userlist;
	}
	
	public static void setActiveUser(User user) {
		activeUser = user;
	}
}