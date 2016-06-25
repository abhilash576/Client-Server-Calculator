package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class Page1 extends HttpServlet {
 
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title></title></head><body>");	  
	 
	 
try {
		
		HttpSession session = request.getSession(true);
	 		 
		 if(session.isNew()){				
			 
				out.println("<p><form action=\"Page2\" method=\"post\"></p>");
				out.println("<p>First Name: <input type=\"text\" name=\"first_nm\"></p>");
				out.println("<p><br /></p>");
				out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
				out.println("<p></form></p>");				
				
		 } else {
				 
			 String first_nm = session.getAttribute("firstname").toString();
			 if (first_nm != null) {
				out.println("<p><form action=\"Page2\" method=\"post\"></p>");
				out.println("<p>First Name: <input type=\"text\" name=\"first_nm\" value=\""+first_nm+"\"></p>");
				out.println("<p><br /></p>");
				out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
				out.println("<p></form></p>");
			 }	else {
				 out.println("<p><form action=\"Page2\" method=\"post\"></p>");
					out.println("<p>First Name: <input type=\"text\" name=\"first_nm\" value=\""+first_nm+"\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");				 
			 }
						 
			 }
		out.println("</body></html>");
		out.close();
		
		
	}catch (Exception e) {e.printStackTrace();
		//response.sendError(500, "Sorry Server encountered an error");
		response.setStatus(500);
		PrintWriter out1 = response.getWriter();
		out1.println("<html><head></head><body><p>Sorry Server encountered an error</p></body></html>");
		return;
	}
  }
   
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
  }
}