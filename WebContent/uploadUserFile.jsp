<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.umsl.java.beans.*"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.io.output.*"%>

<jsp:useBean id="userbean" scope="page"
	class="edu.umsl.java.beans.UserBean" />
<jsp:useBean id="userdao" scope="request"
	class="edu.umsl.java.dao.UserDao" />

<!DOCTYPE html>
<html lang="en">
<head>
<title>UMSL Team-Based Learning</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
	
</script>

<style>
div.relative {
	position: relative;
	left: 30px;
}
</style>
<link href="css/template.css" rel="stylesheet">

</head>
<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="partials/navbarPartial.jsp"%>
		<div class="container-fluid ">
			<%
				File file;
				String fileName = "";
				int maxFileSize = 5000 * 1024;
				int maxMemSize = 5000 * 1024;
				ServletContext context = pageContext.getServletContext();
				String filePath = context.getInitParameter("file-upload");
				//String filePath = context.getInitParameter("c:\\temp\\");

				// Verify the content type
				String contentType = request.getContentType();

				if ((contentType.indexOf("multipart/form-data") >= 0)) {
					DiskFileItemFactory factory = new DiskFileItemFactory();
					// maximum size that will be stored in memory
					factory.setSizeThreshold(maxMemSize);

					// Location to save data that is larger than maxMemSize.
					factory.setRepository(new File("e:\\temp"));

					// Create a new file upload handler
					ServletFileUpload upload = new ServletFileUpload(factory);

					// maximum file size to be uploaded.
					upload.setSizeMax(maxFileSize);

					try {
						// Parse the request to get file items.
						List<FileItem> fileItems = upload.parseRequest(request);

						// Process the uploaded file items
						Iterator<FileItem> i = fileItems.iterator();

						out.println("<html>");
						out.println("<head>");
						out.println("<title>JSP File upload</title>");
						out.println("</head>");
						out.println("<body>");

						while (i.hasNext()) {
							FileItem fi = (FileItem) i.next();
							if (!fi.isFormField()) {
								// Get the uploaded file parameters
								String fieldName = fi.getFieldName();
								fileName = fi.getName();
								boolean isInMemory = fi.isInMemory();
								long sizeInBytes = fi.getSize();

								// Write the file
								if (fileName.lastIndexOf("\\") >= 0) {
									file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
								} else {
									file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
								}
								fi.write(file);
			%>

			<div class="alert alert-success">
				<strong>Success: </strong> Success. Uploaded filename:
				<%=fileName%><!-- filePath -->
			</div>
			<%
				//out.println("Sucess. Uploaded Filename: " + filePath + fileName + "<br>");

							}
						}
						out.println("</body>");
						out.println("</html>");
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else {
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Servlet upload</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("<p>No file uploaded</p>");
					out.println("</body>");
					out.println("</html>");
				}

				//reading

				BufferedReader br = new BufferedReader(new FileReader(filePath + fileName));
				String data;
				try {
					while ((data = br.readLine()) != null && data.length() != 0) {

						String[] str = data.split(",");

						userbean.setSsoid(str[0]);
						userbean.setFname(str[1]);
						userbean.setLname(str[2]);
						userbean.setEmail(str[3]);
						userbean.setRole(Integer.parseInt(str[4]));
						userbean.setDept(str[5]);
						int err = userdao.addUserBulk(userbean);

						//"insert into user (" + "ssoid, fname, lname, email, role, dept)" + "values (?,?,?,?,?,?)");

						if (err > 0) {
			%>

			<div class="alert alert-warning">
				<strong>Warning: </strong> Failed, duplicate entry:
				<%=data%>
			</div>
			<%
				} else {
			%>

			<div class="alert alert-success">
				<strong>Success: </strong> Success, data inserted:
				<%=data%>
			</div>
			<%
				}

					}
				} catch (IOException e) {
					out.println(e.getMessage());
					//HttpSession session = request.getSession();

				}

				br.close();
			%>
		</div>
		<!-- Footer Note -->
		<%@include file="partials/footerPartial.jsp"%>
	</div>
</body>
</html>