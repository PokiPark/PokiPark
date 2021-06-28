package application;

import java.sql.*;
import java.util.*;

import POJO.*;

public class Database {

	final static String dbUrl = "jdbc:mysql://nic-we.de:3306/pokipark";
	final static String dbUsername = "pokipark";
	final static String dbPassword = "pummeluff654";

	private static Connection connection = null;

	private static Statement statement = null;

	private static ResultSet resultSet = null;

	private static ArrayList<User> userlist = new ArrayList<User>();

	private static ArrayList<Poki> pokilist = new ArrayList<Poki>();

	private static ArrayList<PokedexPoki> pokedex = new ArrayList<PokedexPoki>();

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