package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class addQuizServlet
 */
@WebServlet("/AddQuizServlet")
public class AddQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*QuizesDao quizzesDao = null;
		int courseID = Integer.parseInt(request.getParameter("courseID"));
		int quizNum = Integer.parseInt(request.getParameter("quiz_number"));
		int timLim = Integer.parseInt(request.getParameter("time_limit"));
		String strTim = request.getParameter("start_time");
		String endTim = request.getParameter("end_time");
		//int instrId = Integer.parseInt(request.getParameter("instrId"));
		HttpSession session = request.getSession();
		String instrId = String.valueOf(session.getAttribute("userId"));
		System.out.println("I'm here in GET");
		int err = 0;
		
		if (courseID == 0 || quizNum == 0 || timLim == 0 || strTim == null || endTim == null || instrId == null) 
		{
			err = 1;			
		}
		else 
		{
			try 
			{
				quizzesDao = new QuizesDao();
				
				quizzesDao.addQuiz(courseID, quizNum, timLim, strTim, endTim, instrId);	
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("QuizServlet?err=" + err+ "&cid=" + instrId);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		QuizesDao quizzesDao = null;

		int courseID = Integer.parseInt(request.getParameter("course_id"));
		int quizNum = Integer.parseInt(request.getParameter("quiz_number"));
		int timLim = Integer.parseInt(request.getParameter("time_limit"));
		String strTim = request.getParameter("start_time");
		String endTim = request.getParameter("end_time");
		//String creTim = request.getParameter("create_time");
		//int instrId = Integer.parseInt(request.getParameter("instrId"));
		HttpSession session = request.getSession();
		String instrId = String.valueOf(session.getAttribute("userId"));
		System.out.println("Me called >>>>>>>>");
		
		int err = 0;
		
		if (courseID == 0 || quizNum == 0 || timLim == 0 || strTim == null || endTim == null || instrId == null) 
		{
			err = 1;			
		}
		else 
		{
			try 
			{
				//courdao = new CoursesDao();
				quizzesDao = new QuizesDao();
				System.out.println("add ok2");
				quizzesDao.addQuiz(courseID, quizNum, timLim, strTim, endTim, instrId);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("QuizServlet?err=" + err+ "&cid=" + courseID);
	}

}
