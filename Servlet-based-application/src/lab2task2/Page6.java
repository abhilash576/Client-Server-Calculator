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

public class Page6 extends HttpServlet { 
 
  
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	    response.setContentType("text/html"); 
		int id = 0;
		
	 
	try {
			
			 HttpSession session = request.getSession(false);	
		
			if (session != null) {
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document dom;
				Element rootElement = null;
				
				String s1 = session.getAttribute("firstname").toString();
				String s2 = session.getAttribute("languages").toString();
				String s3 = session.getAttribute("days").toString();
				String s4 = session.getAttribute("email").toString();	
				
				File flnm = new File("lab2.xml");
				
				if(flnm.exists()==false){									
					dom = db.newDocument();
					rootElement = dom.createElement("class");
					dom.appendChild(rootElement);
				} else{

					
					dom = db.parse(flnm);
					
					rootElement = dom.getDocumentElement();
					
					NodeList nList = dom.getElementsByTagName("user");
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
												
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						   
						   Element eElement = (Element) nNode;
						   id = Integer.parseInt(eElement.getAttribute("id"));
			
						}
					}
				}
				Element user = dom.createElement("user");
				rootElement.appendChild(user);
				Attr attr = dom.createAttribute("id");
				id ++ ;
				String str1 = Integer.toString(id);
			    attr.setValue(str1);
			    user.setAttributeNode(attr);
				
				Element firstname_val = dom.createElement("firstname_val");
			        firstname_val.appendChild(dom.createTextNode(s1));
			        user.appendChild(firstname_val);
					
				Element languages_val = dom.createElement("languages_val");
			        languages_val.appendChild(dom.createTextNode(s2));
			        user.appendChild(languages_val);
					
				Element days_val = dom.createElement("days_val");
			        days_val.appendChild(dom.createTextNode(s3));
			        user.appendChild(days_val);
				
				Element email_val = dom.createElement("email_val");
			        email_val.appendChild(dom.createTextNode(s4));
			        user.appendChild(email_val);
					
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    DOMSource source = new DOMSource(dom);
			    StreamResult result = new StreamResult(new File("lab2.xml"));
			    transformer.transform(source, result);
			    StreamResult consoleResult = new StreamResult(System.out);
			    transformer.transform(source, consoleResult);
								
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