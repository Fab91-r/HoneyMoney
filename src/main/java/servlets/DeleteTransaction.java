package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.ConnessioneDb;
import manage.GestioneSaldo;

public class DeleteTransaction extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		int id = Integer.parseInt(req.getParameter("transazione"));
		
		try {
			ConnessioneDb.deleteTransazione(id);
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		String messaggio = "TRANSAZIONE CANCELLATA CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);

		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
		
	}

}
