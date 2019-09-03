package manage;

import java.sql.SQLException;

import connections.ConnessioneDb;

public class GestioneSaldo {
	
	static public int getSaldoCorrente (String username) throws ClassNotFoundException, SQLException {
		
		int saldo = ConnessioneDb.getSaldo(username);
		 
		return saldo;  
		
		
	}

}
