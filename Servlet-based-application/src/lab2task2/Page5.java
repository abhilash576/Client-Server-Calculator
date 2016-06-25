package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class Page5 extends HttpServlet {
 
 
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title></title></head><body>");	  
	 
	 
try {
		
		 HttpSession session = request.getSession(false);
		 if(session == null) {			 
			 response.setStatus(403);
			 out.println("<p>Session not found</p>");			 
			 return;
		 }
		
		if (session != null) {
				
				String em = null;
				if (request.getParameter("email") != null && !(request.getParameter("email").isEmpty())) {							  
					em = request.getParameter("email");				  
				 } else {				 
					 response.setStatus(400);
					 out.println("<p>Email Parameter value is expected but not found</p>");
					 return;
				 }
				session.setAttribute("email",em);

				String s1 = session.getAttribute("firstname").toString();
				String s2 = session.getAttribute("languages").toString();
				String s3 = session.getAttribute("days").toString();
				String s4 = session.getAttribute("email").toString();
				
				
				out.println("<p>Please verify the entered values</p>");
				
				out.println("<p>User : " + s1+" "+ s2+" "+ s3+" "+ s4+ "</p>");
				
					out.println("<p><form action=\"Page4\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Previous\" /</p>");
					out.println("<p></form></p>");
					
					out.println("<p><form action=\"Page6\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Submit\" /</p>");
					out.println("<p></form></p>");
											
					out.println("<p><form action=\"Page7\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Cancel\" /</p>");
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