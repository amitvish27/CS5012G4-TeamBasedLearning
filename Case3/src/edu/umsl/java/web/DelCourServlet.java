package edu.umsl.java.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.java.dao.CoursesDao;


/**
 * Servlet implementation class DelCourServlet
 */
@WebServlet("/DelCourServlet")
public class DelCourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelCourServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{//没有insert 只是select 用这个
		
		
		CoursesDao cous_dao = null;

		try
		{
			int cid = Integer.parseInt(request.getParameter("cid"));
			int curpag = Integer.parseInt(request.getParameter("curpg"));
			System.out.println(cid);
			System.out.println("----------");
			System.out.println(curpag);
			cous_dao = new CoursesDao();
			
			cous_dao.delCourseById(cid);

			response.sendRedirect("CoursesServlet?pg=" + curpag);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			response.sendRedirect("CoursesServlet");
		}
	}

}
