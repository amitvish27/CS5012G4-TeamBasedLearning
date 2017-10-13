package edu.umsl.java.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.beans.Problem;
import edu.umsl.java.dao.ProblemDao;

/**
 * Servlet implementation class ListMathServlet
 */
@WebServlet("/listmath")
public class ListMathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProblemDao probdao = null;

		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");

		int pg = 0;
		String initpg = request.getParameter("pg");

		if (initpg != null) {
			pg = Integer.parseInt(initpg);
		}

		if (pg == 0) {
			pg = 1;
		}

		request.setAttribute("crtpg", pg);

		try {
			probdao = new ProblemDao();

			List<Problem> problist = probdao.getProblemListByPage(pg);

			request.setAttribute("problist", problist);

			request.setAttribute("maxordernum", probdao.getMaxOrderNum());

			int cnt = probdao.getProblemCount();

			int totalpg = (int) Math.ceil(cnt / 10.0);

			request.setAttribute("maxpg", totalpg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dispatcher.forward(request, response);
	}

}
