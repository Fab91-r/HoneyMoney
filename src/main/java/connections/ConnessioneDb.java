package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import models.Account;
import models.Categoria;
import models.Transazione;

import java.util.ArrayList;
import java.util.Date;
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
		String query = "select idTransazione, data, descrizione, categoria, importo from transazioni where idAccount = ?;";
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
	
	public static void addDefaultAccount() throws ClassNotFoundException, SQLException  
	{
		String query = "insert into account (username, password) values (?,?);";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setString(1, "default");
		ps.setString(2, "default");
		ps.executeUpdate();
	}
	
	public static int getIdDefaultAccount() throws ClassNotFoundException, SQLException
	{
		String query = "select idAccount from account where username = \'default\';" ;
		Statement statement = connectionDb().createStatement();
		ResultSet result = statement.executeQuery(query);
		int id = 0;
		while(result.next())
		{
			id = result.getInt(1);
			
		}
		 return id;
	}

	public static void addDefaultCategorie() throws ClassNotFoundException, SQLException {
	    
		int idDefaultAccount = ConnessioneDb.getIdDefaultAccount();
		String query = "insert into categorie (account, categoria) values (?,?);";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setInt(1, idDefaultAccount);
		ps.setString(2, "trasporto");
		ps.executeUpdate();
		ps.setInt(1,  idDefaultAccount);
		ps.setString(2, "bollette");
		ps.executeUpdate();
		ps.setInt(1,  idDefaultAccount);
		ps.setString(2, "alimentari");
		ps.executeUpdate();
		ps.setInt(1,  idDefaultAccount);
		ps.setString(2, "altro");
		ps.executeUpdate();
	}

	public static boolean checkCategorie() throws ClassNotFoundException, SQLException {

		int idDefaultAccount = ConnessioneDb.getIdDefaultAccount();
		String query = "select categoria from categorie where account = ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		statement.setInt(1, idDefaultAccount );
		ResultSet result = statement.executeQuery();
		String categoria = null;
		while (result.next()) {
			categoria = result.getString(1);

			if (categoria != null) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Categoria> getCategorie(String username) throws ClassNotFoundException, SQLException {

		int idAccount = ConnessioneDb.getId(username);
		int idDefaultAccount = ConnessioneDb.getIdDefaultAccount();
		List<Categoria> listaCategorie = new ArrayList<>();
		String query = "select idCategoria, categoria from categorie where account = (?) or account = (?);";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		statement.setInt (1, idAccount);
		statement.setInt (2, idDefaultAccount);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			String categoria = result.getString(2);
			Categoria cat = new Categoria(categoria);
			cat.setId(id);
			listaCategorie.add(cat);
		}
		return listaCategorie;
	}
	
	public static void addCategoria (Categoria categoria, String username) throws ClassNotFoundException, SQLException
	{
		int idAccount = ConnessioneDb.getId(username);
		String query = "insert into categorie (account, categoria) values (?, ?)";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setInt(1,  idAccount);
		ps.setString(2,categoria.getCategoria());
		ps.executeUpdate();

	}
	
	public static void updateCategoria(Categoria categoria, int id) throws ClassNotFoundException, SQLException
	{
		String query = "update categorie set categoria = ? where idCategoria = ?";
		PreparedStatement ps = connectionDb().prepareStatement(query);
		ps.setString(1, categoria.getCategoria());
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public static void deleteCategoria(int id) throws ClassNotFoundException, SQLException {
		String query = "delete from categorie where idCategoria = ?;";
		PreparedStatement statement = connectionDb().prepareStatement(query);
		statement.setInt(1, id);
		statement.executeUpdate();

	}
	
	public static int getSpese(String username) throws ClassNotFoundException, SQLException {
		  int id = ConnessioneDb.getId(username);
		  int saldoMese = 0;
		  Date date = new Date();
		  LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		  int month = localDate.getMonthValue();
		  List<Transazione> lista = new ArrayList<Transazione>();
		  lista.addAll(ConnessioneDb.getTransazioni(username));
		  for (Transazione trans : lista) {
		   String[] data = trans.getData().split("-");
		   int mese = Integer.parseInt(data[1]);
		   if (mese == month) {
		    if (trans.getImporto() < 0) {
		     saldoMese += trans.getImporto();
		    }
		   }
		  }
		  return Math.abs(saldoMese);
		 }
	
	public static int getEntrate(String username) throws ClassNotFoundException, SQLException {
		  int id = ConnessioneDb.getId(username);
		  int saldoMese = 0;
		  Date date = new Date();
		  LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		  int month = localDate.getMonthValue();
		  List<Transazione> lista = new ArrayList<Transazione>();
		  lista.addAll(ConnessioneDb.getTransazioni(username));
		  for (Transazione trans : lista) {
		   String[] data = trans.getData().split("-");
		   int mese = Integer.parseInt(data[1]);
		   if (mese == month) {
		    if (trans.getImporto() > 0) {
		     saldoMese += trans.getImporto();
		    }
		   }
		  }
		  return Math.abs(saldoMese);
		 }

	
//problema canc e mod categorie con transazioni, visuali x cat
}
