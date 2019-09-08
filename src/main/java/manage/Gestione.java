package manage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import connections.ConnessioneDb;
import models.Categoria;

public class Gestione {
	
	public static int getSaldoCorrente (String username) throws ClassNotFoundException, SQLException {
		
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
	
	public static String getDataAttuale ()
	{
		Date now = new Date();
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
		String data1 = data.format(now);
		return data1;
	}

}
