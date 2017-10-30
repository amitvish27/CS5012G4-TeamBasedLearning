package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class GetUserServlet
 */
@WebServlet("/GetUserServlet")
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		//     response.sendRedirect("listuser");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao usrdao = null;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		String myid = request.getParameter("id");
		

		try {
			usrdao = new UserDao();

			UserBean usr = usrdao.getUser(myid);
			
			request.setAttribute("usr", usr);
			request.setAttribute("usrdao", usrdao);

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispatcher.forward(request, response);
	}

}
