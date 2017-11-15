package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.QuestionDao;

/**
 * Servlet implementation class DelquestionServlet
 */
@WebServlet("/DelQuestion")
public class DelQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionDao questionDao = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
		    questionDao = new QuestionDao();
			questionDao.delQuestionById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("Question?err=" + 2);
		}
		response.sendRedirect("Question");
	}

}
