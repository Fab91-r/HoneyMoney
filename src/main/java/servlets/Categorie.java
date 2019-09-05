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

public class Categorie extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String scelta = req.getParameter("scelta");
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		List <Categoria> listaCategorie = new ArrayList<>();
		
		try {
			listaCategorie.addAll(ConnessioneDb.getCategorie(user));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		req.setAttribute("listaCategorie", listaCategorie);

		if ("1".equals(scelta)) {
			getServletContext().getRequestDispatcher("/visualizzaCategorie.jsp").forward(req, resp);
		} else if ("2".equals(scelta)) {
			getServletContext().getRequestDispatcher("/inserisciCategorie.jsp").forward(req, resp);
		} else if ("3".equals(scelta)) {
			getServletContext().getRequestDispatcher("/modificaCategorie.jsp").forward(req, resp);
		} else {
			getServletContext().getRequestDispatcher("/cancellaCategorie.jsp").forward(req, resp);
		}

	}

}
