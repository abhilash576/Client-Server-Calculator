package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

// Extend HttpServlet class
public class Page7 extends HttpServlet {
 
 
  
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	    response.setContentType("text/html"); 
		int id = 0;
		
	 
	try {
			
			 HttpSession session = request.getSession(false);	
		
			if (session != null) {
								
				session.invalidate();
				request.getRequestDispatcher("Page1").forward(request, response);		
			
			} else {
				
				response.setStatus(403);
				 PrintWriter out = response.getWriter();
				  out.println("<html><head></head><body><p>Session not found</p></body></html>");
				  return;
				 
			}
				
			
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