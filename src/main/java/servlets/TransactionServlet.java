package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransactionServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String scelta = req.getParameter("scelta");

		if ("1".equals(scelta)) {
			getServletContext().getRequestDispatcher("/inserimentoTransazione.jsp").forward(req, resp);
		} else if ("2".equals(scelta)) {
			getServletContext().getRequestDispatcher("/modificaTransazione.jsp").forward(req, resp);
		}
		else {
			getServletContext().getRequestDispatcher("/cancellaTransazione.jsp").forward(req, resp);
		}
		
	}

}
