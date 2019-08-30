package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.ConnessioneDb;
import models.Transazione;

public class InsertTransactionServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession session = req.getSession();
		String user = (String)session.getAttribute("user");
		String data = req.getParameter("data");
		String descrizione = req.getParameter("descrizione");
		String categoria = req.getParameter("categoria");
		int importo = Integer.parseInt(req.getParameter("importo"));
		Transazione nuovaTransazione = new Transazione (data, descrizione, categoria, importo);
		try {
			ConnessioneDb.addTransazione(user, nuovaTransazione);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String messaggio = "TRANSAZIONE INSERITA CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);
		
		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
	}

}
