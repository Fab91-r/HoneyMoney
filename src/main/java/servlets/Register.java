package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connections.ConnessioneDb;
import models.Account;

public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/registrazione.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String user = req.getParameter("user");
		String pass = req.getParameter("pass");

		try {
			if (ConnessioneDb.checkUser(user)) {

				Account account = new Account(user, pass);
				ConnessioneDb.addAccount(account);
				req.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/registrazioneRiuscita.jsp").forward(req, resp);
			}

			else {
				getServletContext().getRequestDispatcher("/registrazioneFallita.jsp").forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
