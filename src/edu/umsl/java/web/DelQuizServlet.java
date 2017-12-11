package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class DelQuizServlet
 */
@WebServlet("/DelQuizServlet")
public class DelQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelQuizServlet() {
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
			int pid = Integer.parseInt(request.getParameter("pid"));
			
			quizzesDao = new QuizesDao();
			
			quizzesDao.delQuizById(pid);

			response.sendRedirect("LandingQuizServlet");
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			response.sendRedirect("LandingQuizServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
