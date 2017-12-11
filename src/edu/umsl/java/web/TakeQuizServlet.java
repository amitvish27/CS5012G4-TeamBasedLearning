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
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		
		String studentid =(String) session.getAttribute("userId")!=null?(String) session.getAttribute("userId"):"";
		
		TakeQuizDao takeQuizDao = new TakeQuizDao();

		String idstring = (String)request.getParameter("quizid");
		int quizid = Integer.parseInt(!(idstring==null || idstring.equals(""))?idstring:"-1");
		
		String questionNumString = (String)request.getParameter("quizQuestNumber");
		int questionNumber = Integer.parseInt(!(questionNumString==null || questionNumString.equals(""))?questionNumString:"-1");
		
		String relnidStr=(String) request.getParameter("relnid");
		int relnid = Integer.parseInt(!(relnidStr==null || relnidStr.equals(""))?relnidStr:"-1");
		
		String questidStr=(String) request.getParameter("questid");
		int questid = Integer.parseInt(!(questidStr==null || questidStr.equals(""))?questidStr:"-1");
		
		String selectedOptionStr=(String) request.getParameter("selectedOption");
		int selectedOption = Integer.parseInt(!(selectedOptionStr==null || selectedOptionStr.equals(""))?selectedOptionStr:"-1");
		
		String groupidStr=(String) request.getParameter("groupid");
		int groupid = Integer.parseInt(!(groupidStr==null || groupidStr.equals(""))?groupidStr:"-1");
		
		String isgroupquizStr=(String) request.getParameter("isgroupquiz");
		int isgroupquiz = Integer.parseInt(!(isgroupquizStr==null || isgroupquizStr.equals(""))?isgroupquizStr:"-1");
		
		
		switch (action) {
		case "getActiveQuizList":
			String timestamp=(String) request.getParameter("timestamp")!=null?(String) request.getParameter("timestamp"):"";
			JsonArrayBuilder jsonArry = takeQuizDao.getActiveQuizList(studentid, timestamp);
			//check isGroupLeader, if yes return GroupQuizes with Availability Status
			JsonArrayBuilder jsonGroupQuizArry = Json.createArrayBuilder();
			if (takeQuizDao.isGroupLeader(studentid)) {
				jsonGroupQuizArry = takeQuizDao.getActiveGroupQuizList(studentid, timestamp);
			}
			
			jsonObject = Json.createObjectBuilder()
					.add("activeQuizList", jsonArry)
					.add("activeGroupQuizList", jsonGroupQuizArry)
					.build();
			
			break;
		case "getQuizDetails":
			String token = (String) (request.getParameter("token"));
			
			if(!takeQuizDao.checkTokenValidation(studentid, groupid, quizid, token))
			{
				jsonObject = Json.createObjectBuilder()
						.add("error", "Token Validation Failed").build();
			}
			else {
				takeQuizDao.clearAnswersForThisQuiz(relnid);
				jsonObject = Json.createObjectBuilder().add("quiz", 
						takeQuizDao.getQuizWithId(groupid, quizid, 1, relnid, isgroupquiz))
						.add("relnid", relnid)
						.add("groupid", groupid)
						.add("isgroupquiz", isgroupquiz)
						.build();	
			}
			break;
		case "getQuestion":
			jsonObject = Json.createObjectBuilder().add("quiz", 
					takeQuizDao.getQuizWithId(groupid, quizid, questionNumber, relnid, isgroupquiz))
					.add("relnid", relnid)
					.add("groupid", groupid)
					.add("isgroupquiz", isgroupquiz)
					.build();
			break;
		case "submitAnswer":
			if(isgroupquiz==0) {
				takeQuizDao.submitAnswer(relnid, questid, selectedOption);
				jsonObject = Json.createObjectBuilder()
						.add("submitresultstr","Your Answer is submitted!")
						.add("isgroupquiz", isgroupquiz)
						.add("classalert", " alert-info")
						.build();
			} else {
				String questAtmtStr = (String) request.getParameter("questattemptcount");
				int questAtmtCnt = Integer.parseInt(questAtmtStr);
				jsonObject = takeQuizDao.submitGroupAnswer(relnid, questid, selectedOption, questAtmtCnt);  // get instant feedback right/wrong
				
			}
			break;
		case "finishQuiz":
			takeQuizDao.finishQuiz(studentid, groupid, quizid);
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
