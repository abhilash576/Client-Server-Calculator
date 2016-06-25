package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class Page2 extends HttpServlet {
 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	  
	  response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title></title></head><body>");	  
	 
	 
try {
		 HttpSession session = request.getSession(false);		 
		 if(session == null){			 
			 //response.sendError(403, "Session not found" );
			 response.setStatus(403);
			 out.println("<p>Session not found</p>");			 
			 return;
		 }
		 Object obj = session.getAttribute("languages");
		 
		 if (obj == null) {
				
				//String fn = request.getParameter("first_nm");
			 	String fn = null;
				if (request.getParameter("first_nm") != null && !(request.getParameter("first_nm").isEmpty())) {							  
					fn = request.getParameter("first_nm");				  
				 } else {				 
					 //response.sendError(400, "Firstname Parameter value is expected but not found" );
					 response.setStatus(400);
					 out.println("<p>Firstname Parameter value is expected but not found</p>");
					 return;
				 }
				session.setAttribute("firstname",fn);
								
					out.println("<p><form action=\"Page3\" method=\"post\"></p>");
					out.println("<p>Languages: <input type=\"text\" name=\"lang\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");
											
					
					out.println("<p><form action=\"Page1\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Previous\" /</p>");
					out.println("<p></form></p>");
					

		} else {
					
					String lang = obj.toString();
					String fn = request.getParameter("first_nm");				
					if (fn != null) {
						session.setAttribute("firstname",fn);
					}
					out.println("<p><form action=\"Page3\" method=\"post\"></p>");
					out.println("<p>Languages: <input type=\"text\" name=\"lang\" value=\""+lang+"\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");
					
					
					out.println("<p><form action=\"Page1\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Previous\" /</p>");
					out.println("<p></form></p>");
			
				
		 }
		 
		out.println("</body></html>");
		out.close();
		
		
	}catch (Exception e) {e.printStackTrace();
	//response.sendError(500, "Sorry , Server encountered an error");
	response.setStatus(500);
	PrintWriter out1 = response.getWriter();
	out1.println("<html><head></head><body><p>Sorry Server encountered an error</p></body></html>");
	return;
	}
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {  
	  if (request.getMethod().equals("GET")){
		  //response.sendError(405, "Sorry , GET method is not supported.Please check!");
		  response.setStatus(405);
		  PrintWriter out = response.getWriter();
		  out.println("<html><head></head><body><p>Sorry , GET method is not supported.Please check!</p></body></html>");
		  return;
  		}
  }
  
}