package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class Page4 extends HttpServlet {
 
 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title></title></head><body>");	  
	 
	 
try {
		
		 HttpSession session = request.getSession(false);
		 if(session == null){			 
			 response.setStatus(403);
			 out.println("<p>Session not found</p>");			 
			 return;
		 }
		Object obj = session.getAttribute("email");
		if (obj == null) {
				
				String dy = null;
				if (request.getParameter("days") != null && !(request.getParameter("days").isEmpty())) {							  
					dy = request.getParameter("days");				  
				 } else {				 
					 response.setStatus(400);
					 out.println("<p>Days Parameter value is expected but not found</p>");
					 return;
				 }
				session.setAttribute("days",dy);								
								
					out.println("<p><form action=\"Page5\" method=\"post\"></p>");
					out.println("<p>Email: <input type=\"email\" name=\"email\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");
											
					
					
					out.println("<p><form action=\"Page3\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Previous\" /</p>");
					out.println("<p></form></p>");
					
				} else {
					
					String email1 = obj.toString();
					String dy = request.getParameter("days");	
					if (dy != null) {
						session.setAttribute("days",dy);
					}
					out.println("<p><form action=\"Page5\" method=\"post\"></p>");
					out.println("<p>Email: <input type=\"email\" name=\"email\" value=\""+email1+"\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");	

					out.println("<p><form action=\"Page3\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Previous\" /</p>");
					out.println("<p></form></p>");
					
		 }
		 
		 
		 
		out.println("</body></html>");
		out.close();
		
		
	}catch (Exception e) {e.printStackTrace();
	response.setStatus(500);
	PrintWriter out1 = response.getWriter();
	out1.println("<html><head></head><body><p>Sorry Server encountered an error</p></body></html>");
	return;
	}
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {  
	  if (request.getMethod().equals("GET")){
		  response.setStatus(405);
		  PrintWriter out = response.getWriter();
		  out.println("<html><head></head><body><p>Sorry , GET method is not supported.Please check!</p></body></html>");
		  return;
  		}
  } 
  
}