package edu.umsl.java.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserAdminFilter
 */
@WebFilter("/UserAdminFilter")
public class UserAdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public UserAdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy method is called in " + this.getClass().getName());


	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("UserAdminFilter.doFilter ...");
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;  
	   
	    HttpSession session = req.getSession(true);
	    
	    String xx = (String) session.getAttribute("role");
	   System.out.println(xx);
	   
	    if (session.getAttribute("role")!=null) {
	    	System.out.println("this is inside doFilter: role not null");
	    	if ((String) session.getAttribute("role") == "0")  {
	    		System.out.println("this is inside doFilter: role 0");
	    	chain.doFilter(request, response);
	    	}
	    	else {
	    		System.out.println("inside doFilter: not null, not 0 - not authorized");
		    	res.sendRedirect("notAuthorized.jsp"); //or page where you want to 
	    	}
	    }
	    else {
	    	System.out.println("inside doFilter: notAuthorized");
	    	res.sendRedirect("null - notAuthorized.jsp"); //or page where you want to  
	    }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init method is called in " + this.getClass().getName());

	}

}
