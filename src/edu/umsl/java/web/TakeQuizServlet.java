package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.TakeQuizDao;

/**
 * Servlet implementation class TakeQuizServlet
 */
@WebServlet("/TakeQuiz")
public class TakeQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuizServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("takeQuiz.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject jsonObject = null;
		
		String action = ((String) request.getParameter("action")!=null) ? (String) request.getParameter("action"): "getActiveQuizList";
		
		//getActiveQuizList
		//getQuizWithId -authenticate with token - details total questions, total time
		//nextQuestion and previousQuestion with answer for previous if answered -- getQuestion() 
		//finish quiz -- 
		TakeQuizDao takeQuizDao = new TakeQuizDao();
		int quizId, questionNumber;
		String idstring = (String)request.getParameter("quizid");
		String questionNumString = (String)request.getParameter("quizQuestNumber");
		
		switch (action) {
		case "getActiveQuizList":
			String timestamp=(String) request.getParameter("timestamp")!=null?(String) request.getParameter("timestamp"):"";
			JsonArrayBuilder jsonArry = takeQuizDao.getActiveQuizList(timestamp);
			jsonObject = Json.createObjectBuilder().add("activeQuizList", jsonArry).build();
			break;
		case "getQuizDetails":
			quizId = Integer.parseInt(!(idstring==null || idstring.equals(""))?idstring:"-1");
			String token = (String) (request.getParameter("token"));
			
			if(!takeQuizDao.checkTokenValidation(quizId, token))
			{
				jsonObject = Json.createObjectBuilder()
						.add("error", "Token Validation Failed").build();
			}
			else {
				jsonObject = Json.createObjectBuilder().add("quiz", 
						takeQuizDao.getQuizWithId(quizId, 1)).build();
			}
			break;
		case "getQuestion":
			quizId = Integer.parseInt(!(idstring==null || idstring.equals(""))?idstring:"-1");
			questionNumber = Integer.parseInt(!(questionNumString==null || questionNumString.equals(""))?questionNumString:"-1");
			jsonObject = Json.createObjectBuilder().add("quiz", 
					takeQuizDao.getQuizWithId(quizId, questionNumber)).build();
			break;
		default:
			break;
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
