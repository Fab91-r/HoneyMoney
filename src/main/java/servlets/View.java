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

public class View extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String scelta = req.getParameter("scelta");
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		List<Transazione> listaTransazioni = new ArrayList<Transazione>();
		List <Categoria> listaCategorie = new ArrayList<>();

		try {
			listaTransazioni.addAll(ConnessioneDb.getTransazioni(user));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		try {
			listaCategorie.addAll(ConnessioneDb.getCategorie(user));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("listaTransazioni", listaTransazioni);
		req.setAttribute("listaCategorie", listaCategorie);

		if ("1".equals(scelta)) {
			getServletContext().getRequestDispatcher("/transazioniTotali.jsp").forward(req, resp);
		} else if ("2".equals(scelta)) {

			getServletContext().getRequestDispatcher("/transazioniPositive.jsp").forward(req, resp);
		} else if ("3".equals(scelta)) {

			getServletContext().getRequestDispatcher("/transazioniNegative.jsp").forward(req, resp);
		}else {
			getServletContext().getRequestDispatcher("/transazioniPerCategoria.jsp").forward(req, resp);
		}

	}

}
