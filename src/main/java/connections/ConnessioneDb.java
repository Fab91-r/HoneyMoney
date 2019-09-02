package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Account;
import models.Transazione;

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
	
	public static void addTransazione (String username, Transazione transazione, int scelta) throws ClassNotFoundException, SQLException
	{
		String query1 = "select account.idAccount from honeymoney.account where account.username= ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query1);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		int id = 0;
	    while (result.next())
	    {
	    	id = result.getInt(1);
	    }		
				
		String query2 = "insert into honeymoney.transazioni (idAccount, data, descrizione, categoria, importo) values (?, ?, ?, ?, ?);";
		PreparedStatement ps = connectionDb().prepareStatement(query2);
		ps.setInt(1, id);
		ps.setString(2, transazione.getData());
		ps.setString(3, transazione.getDescrizione());
		ps.setString(4, transazione.getCategoria());
		if (scelta == 1)
		{
		ps.setInt(5, transazione.getImporto());
		}
		else
		{
			ps.setInt(5, (scelta)*transazione.getImporto());
		}
		ps.executeUpdate();	

	}
	
	public static int getSaldo(String username) throws SQLException, ClassNotFoundException {
		
		String query1 = "select account.idAccount from honeymoney.account where account.username = ?;";
		PreparedStatement ps1 = connectionDb().prepareStatement(query1);
		ps1.setString(1, username);		
		ResultSet result = ps1.executeQuery();
		int id = 0;
		while(result.next())
		{
			id = result.getInt(1);
		}
		
		String query2 = "select transazioni.importo from honeymoney.transazioni where transazioni.idAccount= ?;";
		PreparedStatement ps2 = connectionDb().prepareStatement(query2);
		ps2.setInt(1, id);
		ResultSet result2 = ps2.executeQuery();
		int saldo = 0;
	    while (result2.next())
	    {
	    	saldo = saldo + result2.getInt(1);
	    }	
	    return saldo;
	}
	
}
