package edu.umsl.java.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao usrdao = null;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
		String ssoid = request.getParameter("ssoid");
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		
		try {
			usrdao = new UserDao();

			UserBean usr = usrdao.getUserSearch(ssoid, fname, lname);
			
			request.setAttribute("usr", usr);
			request.setAttribute("usrdao", usrdao);

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("search_result.jsp");
		//dispatcher.forward(request, response);
		
	}

}
