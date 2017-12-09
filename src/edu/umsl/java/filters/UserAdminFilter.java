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
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("UserAdminFilter.doFilter ...");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(true);
		String user = (String) (session.getAttribute("userId"));
		
		int role_i = (session.getAttribute("userRole") != null) ? (int) (session.getAttribute("userRole")) : 0;

		if (user!=null && (role_i == 2 || role_i == 1)) {
			// System.out.println("inside doFilter -> role: " + role_i);
			chain.doFilter(request, response);
		} else {
			// System.out.println("inside doFilter -> role: " + role_i + "-- not
			// authorized");
			res.sendRedirect("error.jsp"); // or page where you want to
		}
	}

	@Override
	public void destroy() {
		// System.out.println("Destroy method is called in " +
		// this.getClass().getName());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// System.out.println("Destroy method is called in " +
		// this.getClass().getName());
	}
}
