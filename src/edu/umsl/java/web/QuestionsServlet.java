package edu.umsl.java.web;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class QuestionsServlet
 */
@WebServlet("/QuestionsServlet")
public class QuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsServlet() {
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
			String alreadyQuestionsIdListString = request.getParameter("alreadyQuestionsIdListString");
			String[] alreadyQuesIdArray = alreadyQuestionsIdListString.split("$");
			List<String> alreadyQuesIdList = new ArrayList<String>();
			for(int i=0; i<alreadyQuesIdArray.length; i++)
			{
				alreadyQuesIdList.add(alreadyQuesIdArray[i]);
			}
			
			
			int currQuizId = Integer.parseInt(request.getParameter("currQuizId"));
			
			quizzesDao = new QuizesDao();
			List<QuestionBean> QuestionBeanList = quizzesDao.getAllQuestions();
			List<QuesQuizBean> quesQuizBeanList = new ArrayList<>();
			request.setAttribute("quesQuizBeanList", quesQuizBeanList);
			
			request.setAttribute("QuestionBeanList", QuestionBeanList);
			request.setAttribute("currQuizId", "" + currQuizId);
			request.setAttribute("alreadyQuesIdList", alreadyQuesIdList);
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
