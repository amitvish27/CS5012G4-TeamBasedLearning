package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class DelRelationshipServlet
 */
@WebServlet("/DelRelationshipServlet")
public class DelRelationshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelRelationshipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CoursesDao cous_dao = null;
		QuizesDao quizzesDao = null;
		try
		{
			int relnid = Integer.parseInt(request.getParameter("relnid"));
			
			quizzesDao = new QuizesDao();
			
			quizzesDao.delRelationshipById(relnid);

			response.sendRedirect("CoursesServlet");
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			response.sendRedirect("CoursesServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
