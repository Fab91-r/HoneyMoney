package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Account;

public class ConnessioneDb {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/honeymoney?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "dstech";

	public static Connection connectionDb() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
	}

	public static boolean checkUser(String username) throws ClassNotFoundException, SQLException {
		Statement statement = connectionDb().createStatement();
		String query = "select account.username from honeymoney.account;";
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			String user = result.getString(1);
			if (username.equals(user)) {
				return false;
			}
		}
		return true;
	}

	
	public static void addAccount(Account account) throws ClassNotFoundException, SQLException
	{
		String query = "insert into honeymoney.account (username, password) values (?, ?);";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setString(1, account.getUsername());
		ps.setString(2, account.getPassword());
		ps.executeUpdate();
	}
	
	public static boolean checkLogin(Account account) throws SQLException, ClassNotFoundException {
		String query = "select account.username, account.password from honeymoney.account;";
		Statement statement = connectionDb().createStatement();
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			String user = result.getString(1);
			String pass = result.getString(2);
			if (account.getUsername().equals(user) && account.getPassword().equals(pass)) {
				return true;
			}
		}
		return false;

	}
	
}
