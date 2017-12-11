package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.umsl.java.beans.QuesQuizBean;
import edu.umsl.java.beans.QuestionBean;
import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class QuestionsUnderQuizServlet
 */
@WebServlet("/QuestionsUnderQuizServlet")
public class QuestionsUnderQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsUnderQuizServlet() {
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
			quizzesDao = new QuizesDao();
			int qid = Integer.parseInt(request.getParameter("qid"));
			System.out.println("under servlet: " + qid);//$
			List<QuesQuizBean> quesQuizBeanList = quizzesDao.getQuesQuizInfo(qid);
			List<QuestionBean> QuestionBeanList = quizzesDao.getAllQuestions();
			request.setAttribute("QuestionBeanList", QuestionBeanList);
			request.setAttribute("quesQuizBeanList", quesQuizBeanList);
			
			System.out.println("Question_Quiz: " + quesQuizBeanList.size());//$
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("questionsUnderQuiz.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
