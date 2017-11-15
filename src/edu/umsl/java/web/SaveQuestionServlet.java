package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.QuestionDao;

/**
 * Servlet implementation class SaveQuestionServlet
 */
@WebServlet("/SaveQuestion")
public class SaveQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		QuestionDao questionDao = null;

		try {
			String content = request.getParameter("content");
			String opta = request.getParameter("opta");
			String optb = request.getParameter("optb");
			String optc = request.getParameter("optc");
			String optd = request.getParameter("optd");
			String answer = request.getParameter("answer");
			String kywd = request.getParameter("kywd");
			String instructor = (String) session.getAttribute("userId");
			questionDao = new QuestionDao();

			int id = Integer.parseInt(request.getParameter("id"));

			questionDao.saveQuestion(content, opta, optb, optc, optd, answer, kywd, instructor, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("");
		out.flush();
		out.close();
	}
}
