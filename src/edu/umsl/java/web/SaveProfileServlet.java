package edu.umsl.java.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.umsl.java.beans.UserBean;
import edu.umsl.java.dao.UserDao;

/**
 * Servlet implementation class SaveProfileServlet
 */
@WebServlet("/SaveProfile")
public class SaveProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String modifiedby = (String) session.getAttribute("userId");
		if (modifiedby == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		UserDao instDao = null;
		String divclass = "modal-dialog modal-sm alert ";
		String msgText = "";

		try {
			String type = request.getParameter("type");
			// if type profile then do save Profile
			if (type.equals("profileUpdate")) {
				// get parameters needed to update the profile
				instDao = new UserDao();
				String ssoid = request.getParameter("ssoid");
				String fname = request.getParameter("fname");
				String lname = request.getParameter("lname");
				String email = request.getParameter("email");
				String dept = request.getParameter("dept");

				instDao.saveInstProfile(ssoid, fname, lname, email, dept, modifiedby);
				divclass += "alert-success";
				msgText = "Profile update Successful.";
				session.setAttribute("userFirstName", fname);
				session.setAttribute("userLastName", lname);

			}
			// else if type password then dp save passwd
			else if (type.equals("pswdUpdate")) {
				// first check password
				instDao = new UserDao();
				String ssoid = request.getParameter("ssoid");
				String currpwd = request.getParameter("currpwd");
				String newpwd = request.getParameter("newpwd");
				UserBean inst = instDao.getInstructorBySsoId(ssoid);
				if (inst.getPswd().equals(currpwd)) {
					// update password
					instDao.saveInstPswd(ssoid, newpwd, modifiedby);
					divclass += "alert-success";
					msgText = "Password update Successful.";
				} else {
					// password not match
					divclass += "alert-danger";
					msgText = "Your password is incorrect.";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String htmlData = "<div class=\"" + divclass
				+ "\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n"
				+ "							aria-label=\"Close\">\r\n"
				+ "							<span aria-hidden=\"true\">&times;</span>\r\n"
				+ "						</button><p>" + msgText + "</p></div>";

		out.println(htmlData);
		out.flush();
		out.close();
	}

}
