package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.ConnessioneDb;
import models.Categoria;
import models.Transazione;

public class DeleteCategoria extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
        int idCategoria = Integer.parseInt(req.getParameter("categoria"));
        List<Transazione> listaTransazioniPerCategoria = new ArrayList<>();
        String categoria = null;
		try {
			categoria = ConnessioneDb.getCategoria(idCategoria);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		Categoria cat = new Categoria(categoria);
        try {
        	listaTransazioniPerCategoria.addAll(ConnessioneDb.getTransazioniPerCategoria(cat, user));
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
        if(listaTransazioniPerCategoria.isEmpty())
        {
		try {
			
			ConnessioneDb.deleteCategoria(idCategoria);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String messaggio = "CATEGORIA ELIMINATA CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);
		
        }
        else
        {
        	String messaggio = "IMPOSSIBILE ELIMINARE LA CATEGORIA POICHE' ESISTONO TRANSAZIONI CON QUELLA CATEGORIA! ELIMINARE PRIMA LE RELATIVE TRANSAZIONI!";
    		req.setAttribute("messaggio2", messaggio);
        }

		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
	}
}
