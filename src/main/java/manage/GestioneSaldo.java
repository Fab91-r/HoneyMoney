package manage;

import java.sql.SQLException;

import connections.ConnessioneDb;

public class GestioneSaldo {
	
	static public int getSaldoCorrente (String username) throws ClassNotFoundException, SQLException {
		
		int saldo = ConnessioneDb.getSaldo(username);		 
		return saldo;  	
	}
	
	public static int getSaldoNegativoMeseCorrente(String username) throws ClassNotFoundException, SQLException
	{
		int saldo = ConnessioneDb.getSpese(username);
		return saldo;
	}
	
	public static int getSaldoPositivoMeseCorrente(String username) throws ClassNotFoundException, SQLException
	{
		int saldo = ConnessioneDb.getEntrate(username);
		return saldo;
	}

}
