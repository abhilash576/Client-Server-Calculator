package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Extend HttpServlet class
public class Page3 extends HttpServlet {
 
 
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
		Object obj = session.getAttribute("days");
		if (obj == null) {
				
				String lg = null;	
				if (request.getParameter("lang") != null && !(request.getParameter("lang").isEmpty())) {							  
					lg = request.getParameter("lang");				  
				 } else {				 
					 response.setStatus(400);
					 out.println("<p>Languages Parameter value is expected but not found</p>");
					 return;
				 }
				session.setAttribute("languages",lg);								
								
					out.println("<p><form action=\"Page4\" method=\"post\"></p>");
					out.println("<p>Days: <input type=\"text\" name=\"days\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");
											
					
					out.println("<p><form action=\"Page2\" method=\"post\"></p>");
					out.println("<p><input type=\"Submit\" value=\"Previous\" /</p>");
					out.println("<p></form></p>");
					
				} else {
					
					String days = obj.toString();
					
					String lg = request.getParameter("lang");				
					if (lg != null) {
						session.setAttribute("languages",lg);
					}
					out.println("<p><form action=\"Page4\" method=\"post\"></p>");
					out.println("<p>Days: <input type=\"text\" name=\"days\" value=\""+days+"\"></p>");
					out.println("<p><br /></p>");
					out.println("<p><input type=\"Submit\" value=\"Next\" /</p>");
					out.println("<p></form></p>");	

					out.println("<p><form action=\"Page2\" method=\"post\"></p>");
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