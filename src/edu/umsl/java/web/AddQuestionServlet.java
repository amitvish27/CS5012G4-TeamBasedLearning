package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.dao.QuestionDao;

/**
 * Servlet implementation class AddQuestionServlet
 */
@WebServlet("/AddQuestion")
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuestionDao questionDao = null;
		HttpSession session = request.getSession();
		// String q_course = request.getParameter("q_Course");
		// String q_topic = request.getParameter("q_Topic");

		String questionCont = request.getParameter("questionCont");
		String questionOptA = request.getParameter("questionOptA");
		String questionOptB = request.getParameter("questionOptB");
		String questionOptC = request.getParameter("questionOptC");
		String questionOptD = request.getParameter("questionOptD");
		String questionAnswer = request.getParameter("questionAnswer");
		String questionKeyword = request.getParameter("questionKeyword");
		String instructor = (String) session.getAttribute("userId");
		int err = 0;
		if (questionCont == null || questionCont.length() == 0 || questionOptA == null || questionOptA.length() == 0
				|| questionOptB == null || questionOptB.length() == 0 || questionOptC == null
				|| questionOptC.length() == 0 || questionOptD == null || questionOptD.length() == 0
				|| questionAnswer == null || questionAnswer.length() == 0) {
			err = 1;
		} else {
			try {
				questionDao = new QuestionDao();
				questionDao.addQuestion(questionCont, questionOptA, questionOptB, questionOptC, questionOptD,
						questionAnswer, questionKeyword, instructor);

			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("Question?err=" + 2);
			}
		}

		response.sendRedirect("Question?err=" + err);
	}

}
