package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connections.ConnessioneDb;
import models.Account;



public class LoginFilter implements Filter{
	
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
			this.context = fConfig.getServletContext();
		}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String user =req.getParameter("user");
		String pass = req.getParameter("pass");
		Account account = new Account (user, pass);
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60*30);
		session.setAttribute("user", user);
		try {
			if (!(ConnessioneDb.checkCategorie(user)))
			{
				ConnessioneDb.addDefaultCategorie(user);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(ConnessioneDb.checkLogin(account)) {
				chain.doFilter(req, res);
			}
			else {
				String messaggio = "LOGIN FALLITO UTENTE NON REGISTRATO O DATI ERRATI";
				req.setAttribute("messaggio", messaggio);
				req.getRequestDispatcher("/intro.jsp").forward(req, res);
							
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
				
	}

}