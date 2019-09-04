package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Account;
import models.Categoria;
import models.Transazione;

import java.util.ArrayList;
import java.util.List;

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

	public static void addAccount(Account account) throws ClassNotFoundException, SQLException {
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

	public static int getId(String username) throws ClassNotFoundException, SQLException {
		String query1 = "select account.idAccount from honeymoney.account where account.username= ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query1);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		int id = 0;
		while (result.next()) {
			id = result.getInt(1);
		}
		return id;
	}

	public static void addTransazione(String username, Transazione transazione, int scelta)
			throws ClassNotFoundException, SQLException {

		int id = ConnessioneDb.getId(username);
		String query = "insert into honeymoney.transazioni (idAccount, data, descrizione, categoria, importo) values (?, ?, ?, ?, ?);";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setInt(1, id);
		ps.setString(2, transazione.getData());
		ps.setString(3, transazione.getDescrizione());
		ps.setString(4, transazione.getCategoria());
		if (scelta == 1) {
			ps.setInt(5, transazione.getImporto());
		} else {
			ps.setInt(5, (scelta) * transazione.getImporto());
		}
		ps.executeUpdate();

	}

	public static int getSaldo(String username) throws SQLException, ClassNotFoundException {

		int id = ConnessioneDb.getId(username);
		String query = "select transazioni.importo from honeymoney.transazioni where transazioni.idAccount= ?;";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setInt(1, id);
		ResultSet result = ps.executeQuery();
		int saldo = 0;
		while (result.next()) {
			saldo = saldo + result.getInt(1);
		}
		return saldo;
	}

	public static List<Transazione> getTransazioni(String username) throws ClassNotFoundException, SQLException {

		List<Transazione> listaTransazioni = new ArrayList<Transazione>();
		int idAccount = ConnessioneDb.getId(username);
		String query = "select idtransazione, data, descrizione, categoria, importo from transazioni where idAccount = ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		statement.setInt(1, idAccount);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			int idTrans = result.getInt(1);
			String data = result.getString(2);
			String descrizione = result.getString(3);
			String categoria = result.getString(4);
			int importo = result.getInt(5);
			Transazione transazione = new Transazione(data, descrizione, categoria, importo);
			transazione.setId(idTrans);
			listaTransazioni.add(transazione);
		}
		return listaTransazioni;
	}

	public static void updateTransazione(Transazione transazione, int id, int scelta)
			throws ClassNotFoundException, SQLException {

		String query = "update transazioni set data = ?, descrizione = ?, categoria = ?, importo = ? where idTransazione = ?";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setString(1, transazione.getData());
		ps.setString(2, transazione.getDescrizione());
		ps.setString(3, transazione.getCategoria());
		if (scelta == 1) {
			ps.setInt(4, transazione.getImporto());
		} else {
			ps.setInt(4, (scelta) * transazione.getImporto());
		}
		ps.setInt(5, id);
		ps.executeUpdate();

	}

	public static void deleteTransazione(int id) throws ClassNotFoundException, SQLException {
		String query = "delete from transazioni where idTransazione = ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();

	}
	

	public static void addDefaultCategorie() throws ClassNotFoundException, SQLException {

		String query1 = "insert into categorie (categoria) values (?);";
		PreparedStatement ps1 = connectionDb().prepareStatement(query1);
		ps1.setString(1, "trasporto");
		ps1.executeUpdate();
		ps1.setString(1, "bollette");
		ps1.executeUpdate();
		ps1.setString(1, "alimentari");
		ps1.executeUpdate();
		ps1.setString(1, "altro");
		ps1.executeUpdate();
	}

	public static boolean checkCategorie() throws ClassNotFoundException, SQLException {

		String query = "select categoria from categorie;";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		ResultSet result = statement.executeQuery(query);
		String categoria = null;
		while (result.next()) {
			categoria = result.getString(1);

			if (categoria != null) {
				return true;
			}
		}
		return false;
	}
	
	public static List<String> getCategorie() throws ClassNotFoundException, SQLException {

		List<String> listaCategorie = new ArrayList<>();
		String query = "select categoria from categorie;";
		Statement statement = connectionDb().createStatement();
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			String categoria = result.getString(1);
			listaCategorie.add(categoria);
		}
		return listaCategorie;
	}
	
	public static void addCategoria (String categoria) throws ClassNotFoundException, SQLException
	{
		String query = "insert into categorie (categoria) values (?)";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setString(1,categoria);
		ps.executeUpdate();

	}
	
	public static void updateCategoria(String categoria, int id) throws ClassNotFoundException, SQLException
	{
		String query = "update categorie set categoria = ? where idCategoria = ?";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setString(1, categoria);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public static void deleteCategoria(int id) throws ClassNotFoundException, SQLException {
		String query = "delete from categorie where idCategoria = ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();

	}
	

}
