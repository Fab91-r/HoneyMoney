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
import models.Transazione;

public class ModifyTransaction extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		int importo = Integer.parseInt(req.getParameter("importo"));
		int scelta = Integer.parseInt(req.getParameter("scelta"));
		int id = Integer.parseInt(req.getParameter("transazione"));
		String data = req.getParameter("data");
		String descrizione = req.getParameter("descrizione");
		String categoria = req.getParameter("categoria");
         Transazione transazioneAggiornata = new Transazione (data, descrizione, categoria, importo);
		try {
			ConnessioneDb.updateTransazione(transazioneAggiornata, id, scelta);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


		String messaggio = "TRANSAZIONE MODIFICATA CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);

		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
	}

}