package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.umsl.java.beans.QuizSetupBean;
import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizesDao cous_dao = null;
		try
		{
			//HttpSession session = request.getSession();
			//String instSSOid = String.valueOf(session.getAttribute("cid"));
			
			String cidParam = request.getParameter("cid");
			int currCid = Integer.parseInt(cidParam);
			cous_dao = new QuizesDao();
			List<QuizSetupBean> quizBeansetupList = cous_dao.getQuizNumUnderCourse(currCid);
			System.out.println(quizBeansetupList.size());
			System.out.println("ok");
			request.setAttribute("quizBeansetupList", quizBeansetupList);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("seequiz.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
