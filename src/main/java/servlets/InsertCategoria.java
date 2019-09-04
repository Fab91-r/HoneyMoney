package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.ConnessioneDb;
import models.Categoria;

public class InsertCategoria extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");

		String categoria = req.getParameter("categoria");

		try {
			ConnessioneDb.addCategoria(categoria);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String messaggio = "CATEGORIA INSERITA CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);

		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
	}
}
