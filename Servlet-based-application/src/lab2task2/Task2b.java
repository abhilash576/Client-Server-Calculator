package lab2task2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.*;



public class Task2b extends HttpServlet {
 
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {      
      response.setContentType("text/html");
	  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title></title></head><body>");
     Map<String, Integer> totals = new HashMap<String, Integer>();
    
	  String firstnm_xml1 = null;
	  String[] lang_xml1 = null;
	  String[] days_xml1 = null;
	  String email_xml1 = null;
	  String s1 = null;
	  
	  String firstnm_xml = null;
	  String[] lang_xml = null;
	  String[] days_xml = null;
	  String email_xml = null;
	  
	  String firstname1 = null;
		String lang[] = null;
		String days[] = null;
		String email = null;
	  
	  Cookie cookie = null;
	  Cookie[] cookies = null;	  
	  cookies = request.getCookies();
	  
	  int count = 0;
	  int unt = 0;
	  
	  String id = null;
	  String id1 = null;
	  String fn = null;
	  String ln = null;
	   
try {
	
		if( cookies == null ){
			
			Cookie namecookie = new Cookie("cookie1","abc");
			namecookie.setMaxAge(60*60*24);
			response.addCookie(namecookie);			
			request.getRequestDispatcher("/lab2task2/Home.htm").forward(request, response);
			
			
		} else {
						
			fn = request.getParameter("first_nm");
			ln = request.getParameter("last_nm");
						
			if(fn == null && ln == null){
				
				for (int i = 0; i < cookies.length; i++){
						cookie = cookies[i];
					
					if((cookie.getName( )).compareTo("first_name") == 0 ){									
							s1 = cookie.getValue( );
							out.print("Welcome again : " + cookie.getValue( )+" <br/>");							
					}
				}
				
				if (s1 != null && !(s1.isEmpty())) {							  
									  
				 } else {				 
					 
					 response.setStatus(400);
					 out.println("<p>Firstname Parameter value is expected but not found</p>");
					 return;
				 }
								
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document dom;
				Element rootElement = null;
				
				File flnm = new File("lab2.xml");
				
				if(flnm.exists()==false){
							out.println("<p> Xml file doesnot exist to fetch the records </p>");							
					}
					else{						
							
							dom = db.parse(flnm);
							dom.getDocumentElement().normalize();
							rootElement = dom.getDocumentElement();												
							NodeList nList = dom.getElementsByTagName("user");
							for (int temp = 0; temp < nList.getLength(); temp++) {
								
								Node nNode = nList.item(temp);																					
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								   
									Element eElement = (Element) nNode;
									
									 firstnm_xml = eElement.getElementsByTagName("firstname_val").item(0).getTextContent();
									 lang_xml = eElement.getElementsByTagName("languages_val").item(0).getTextContent().split(" ");
									 days_xml = eElement.getElementsByTagName("days_val").item(0).getTextContent().split(" ");
									 email_xml = eElement.getElementsByTagName("email_val").item(0).getTextContent();
								}
										
								if((firstnm_xml.toLowerCase().contains(s1.toLowerCase()))) {
																	
									//out.println("<p>Entered User Preferences  : " + firstnm_xml+" "+Arrays.toString(lang_xml)+" "+Arrays.toString(days_xml)+" "+email_xml+ "</p>");
									count = 1;
									break;
															
								}					 

							}
						}
						
			if ( count == 1 ){							  
				out.println("------------------------------------------------------------------------------------<br>");
								
						if(flnm.exists()==false){													
							 out.println("<p> Xml file doesnot exist to fetch the records </p>");
						}
						else{
							out.println("Users with similar preference of languages attribute:");
							dom = db.parse(flnm);
							dom.getDocumentElement().normalize();
							rootElement = dom.getDocumentElement();												
							NodeList nList = dom.getElementsByTagName("user");
							for (int temp = 0; temp < nList.getLength(); temp++) {
								int ount = 0;
								
								boolean str1 = false ;
								
								boolean namecheck = false;
								
								Node nNode = nList.item(temp);
																
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								   
									Element eElement = (Element) nNode;
									 id = eElement.getAttribute("id");	
									 firstnm_xml1 = eElement.getElementsByTagName("firstname_val").item(0).getTextContent();
									 lang_xml1 = eElement.getElementsByTagName("languages_val").item(0).getTextContent().split(" ");
									 days_xml1 = eElement.getElementsByTagName("days_val").item(0).getTextContent().split(" ");
									 email_xml1 = eElement.getElementsByTagName("email_val").item(0).getTextContent();
								}
								
								if (lang_xml !=null) {	
												
									for(int i =0;i<lang_xml.length;i++) {
										for(int j=0;j<lang_xml1.length;j++) {
											if(lang_xml[i].equalsIgnoreCase(lang_xml1[j])) {
												 str1 = true;
												ount = ount + 1;
											}
										}
									}
								}
															
											if(s1 != null){
												if(firstnm_xml1.toLowerCase().contains(s1.toLowerCase()))
													namecheck = true;
											}
											
											if (namecheck) {												
												
											} else if ((str1)) {	
																								
												if (ount != 0) {
													
													out.println("<p>"+ firstnm_xml1+ "</p>");
												}
												
											}												
										}											
									}	
						
						
						
						if(flnm.exists()==false){													
							 out.println("<p> Xml file doesnot exist to fetch the records </p>");
						}
						else{
							out.println("Users with similar preference of days attribute:");
							dom = db.parse(flnm);
							dom.getDocumentElement().normalize();
							rootElement = dom.getDocumentElement();												
							NodeList nList = dom.getElementsByTagName("user");
							for (int temp = 0; temp < nList.getLength(); temp++) {
								
								int dount = 0;
								
								boolean str2 = false;
								boolean namecheck = false;
								
								Node nNode = nList.item(temp);
																
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								   
									Element eElement = (Element) nNode;
									 id = eElement.getAttribute("id");	
									 firstnm_xml1 = eElement.getElementsByTagName("firstname_val").item(0).getTextContent();
									 lang_xml1 = eElement.getElementsByTagName("languages_val").item(0).getTextContent().split(" ");
									 days_xml1 = eElement.getElementsByTagName("days_val").item(0).getTextContent().split(" ");
									 email_xml1 = eElement.getElementsByTagName("email_val").item(0).getTextContent();
								}
								
																	
											
									if (days_xml !=null) {													
										for(int i =0;i<days_xml.length;i++) {
											for(int j=0;j<days_xml1.length;j++) {
												if(days_xml[i].equalsIgnoreCase(days_xml1[j])) {
														 str2 = true;
														dount = dount + 1;
																											
													}
												}
											}
									}
											if(s1 != null){
												if(firstnm_xml1.toLowerCase().contains(s1.toLowerCase()))
													namecheck = true;
											}
											
											if (namecheck) {												
												
											} else if ((str2)) {														
												
												if (dount != 0) {
													
													out.println("<p>"+ firstnm_xml1+ "</p>");
												}
												
											}												
										}											
									}
						
								}		
					
			} else{
				if (request.getParameter("first_nm") != null && !(request.getParameter("first_nm").isEmpty())) {							  
					fn = request.getParameter("first_nm");				  
				 } else {				 
					 response.setStatus(400);
					 out.println("<p>Firstname Parameter value is expected but not found</p>");
					 return;
				 }
				
				if (request.getParameter("last_nm") != null && !(request.getParameter("last_nm").isEmpty())) {							  
					ln = request.getParameter("last_nm");				  
				 } else {				 
					 response.setStatus(400);
					 out.println("<p>Lastname Parameter value is expected but not found</p>");
					 return;
				 }
				
				Cookie firstname = new Cookie("first_name", request.getParameter("first_nm"));
					Cookie lastname = new Cookie("last_name", request.getParameter("last_nm"));
					firstname.setMaxAge(60*60*24);
					lastname.setMaxAge(60*60*24);
					response.addCookie(firstname);
					response.addCookie(lastname);
					out.println("Hello there," + fn +" "+ ln);
					out.println("Welcome for the first time");
			
			}			
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
  
	
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	  doGet(request,response);
  }
}