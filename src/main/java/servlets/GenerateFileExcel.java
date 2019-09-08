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
import excel.FileExcel;
import models.Transazione;

public class GenerateFileExcel extends HttpServlet{
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		List<Transazione> listaTransazioni = new ArrayList<Transazione>();
		
		try {
			listaTransazioni.addAll(ConnessioneDb.getTransazioni(user));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace(); 
		}
		
		FileExcel.createFileExcel(listaTransazioni);
		
		String messaggio = "FILE GENERATO CON SUCCESSO!";
		req.setAttribute("messaggio", messaggio);

		getServletContext().getRequestDispatcher("/benvenuto.jsp").forward(req, resp);
	}

}
