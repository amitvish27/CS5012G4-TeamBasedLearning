package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.QuizesDao;

/**
 * Servlet implementation class GetQuizServlet
 */
@WebServlet("/GetQuizServlet")
public class GetQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//CoursesDao cous_dao = null;
		QuizesDao quizzesDao = null;
		String[] quizInfoArr = new String[7];
		String beSentStr = "";

		try 
		{
			int qid = Integer.parseInt(request.getParameter("qid"));
			
			quizzesDao = new QuizesDao();

			quizInfoArr = quizzesDao.getQuizInfoById(qid);
			
			for(int i=0; i<quizInfoArr.length; i++)
			{
				if(i == (quizInfoArr.length-1))
				{
					beSentStr = beSentStr + quizInfoArr[i];
					break;
				}
				
				beSentStr = beSentStr + quizInfoArr[i] + "$HA$" ;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		out.println(beSentStr);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
