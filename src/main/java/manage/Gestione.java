package manage;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.tools.javac.util.List;

import connections.ConnessioneDb;
import models.Categoria;

public class Gestione {
	
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
	
	
	public static ArrayList<Categoria> getListaCategorieList(String username) throws ClassNotFoundException, SQLException
	{	
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		lista.addAll(ConnessioneDb.getCategorie(username));
		
		return lista;
	}

}
