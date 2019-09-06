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

public class ViewPerCategoria extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		String categoria = req.getParameter("categoria");
		List<Transazione> listaTransazioniPerCategoria = new ArrayList<>();

		try {
			listaTransazioniPerCategoria.addAll(ConnessioneDb.getTransazioniPerCategoria(categoria));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


		req.setAttribute("lista", listaTransazioniPerCategoria);
		
		
		getServletContext().getRequestDispatcher("/visualizzaPerCategoria.jsp").forward(req, resp);
	}

}
