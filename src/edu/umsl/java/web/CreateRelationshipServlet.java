package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class CreateRelationshipServlet
 */
@WebServlet("/CreateRelationshipServlet")
public class CreateRelationshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRelationshipServlet() {
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
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			int currQuizId = Integer.parseInt(request.getParameter("currQuizId"));
			
			quizzesDao = new QuizesDao();
			quizzesDao.createRelationship(questionId, currQuizId);
			request.setAttribute("currQuizId", currQuizId);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Courses_list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
