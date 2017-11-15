package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.QuestionDao;

/**
 * Servlet implementation class GetQuestionServlet
 */
@WebServlet("/GetQuestion")
public class GetQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		QuestionDao questionDao = null;
		String[] question = new String[7];
		String title = "";

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			questionDao = new QuestionDao();
			question = questionDao.getQuestionById(id);

			title = "<textarea id=\"titlearea" + id + "\" rows=\"2\" class=\"form-control\">" + question[0]
					+ "</textarea>";

		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println(title);
		out.flush();
		out.close();
	}
}
