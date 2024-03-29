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

public class ModifyCategoria extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		int idCategoria = Integer.parseInt(req.getParameter("oldCategoria"));
		
		String oldCategoria = null;
		try {
			oldCategoria = ConnessioneDb.getCategoria(idCategoria);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		String newCategoria = req.getParameter("newCategoria");
		Categoria cat1 = new Categoria(oldCategoria);
		Categoria cat2 = new Categoria(newCategoria);
		

		try {
			ConnessioneDb.updateCategoria(cat2, idCategoria);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ConnessioneDb.updateTransazioniPerCategoria(cat1, cat2, user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String messaggio = "CATEGORIA MODIFICATA CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);

		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
	}

}
