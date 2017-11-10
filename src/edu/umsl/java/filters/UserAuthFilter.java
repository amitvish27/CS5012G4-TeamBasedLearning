package edu.umsl.java.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAuthFilter implements Filter {

	private String contextPath;

	  @Override
	  public void init(FilterConfig fc) throws ServletException {
	    contextPath = fc.getServletContext().getContextPath();
	  }

	  @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;  

	    if (req.getSession().getAttribute("role") == null) { //checks if there's a LOGIN_USER set in session...
	        res.sendRedirect(contextPath + "login.jsp"); //or page where you want to redirect
	    } else {
	      String userType = (String) req.getSession().getAttribute("role");
	      if (!userType.equals("0")){ //check if user type is not admin
	        res.sendRedirect(contextPath + "login.jsp"); //or page where you want to  
	      }
	      fc.doFilter(request, response);
	    }
	  }

	  @Override
	  public void destroy() {
	  }

	

}