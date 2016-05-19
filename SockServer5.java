
import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class SockServer5 {

	public static void main(String args[]) {
		
		ServerSocket s1 = null;
		InputStream in = null;
		OutputStream out = null;
		Socket s2 = null;
		
		int sum = 0;
		int x = 0;		
		    
		
		try {
			
			s1 = new ServerSocket(8888);			
			System.out.println("Server started running.......");
			Map<String, String> totals = new HashMap<String, String>();	

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			// check if in command line arguments "xml" keyword is passed then read the existing xml else if "new" keyword is passed then do no thing
			
			if ((args[0]).equals("xml")) {					
					
					try{
						
					File flnm = new File("xmltest.xml");
					Document dom = db.parse(flnm);
				
					dom.getDocumentElement().normalize();
					System.out.println("Root element :" + dom.getDocumentElement().getNodeName());					
					NodeList nList = dom.getElementsByTagName("client");
					System.out.println("----------------------------");
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						   
						   Element eElement = (Element) nNode;
						   System.out.println("Client id no : " + eElement.getAttribute("id"));
						   System.out.println("Client id value : " + eElement.getElementsByTagName("value").item(0).getTextContent());
						   totals.put(eElement.getAttribute("id"),eElement.getElementsByTagName("value").item(0).getTextContent()+"");
						   
						}
					}
				
					}catch(FileNotFoundException e){
						System.out.println("Please create an xml before trying to read.Run the code with 'new'  keyword as an argument");
						System.exit(1);
					}
			} else if ((args[0]).equals("new")) {						
					
			}
			
			while (true) {				
				
				s2 = s1.accept();
				
				in = s2.getInputStream();
				out = s2.getOutputStream();			
				Document dom = db.newDocument(); 
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out), true);					
				BufferedReader br = new BufferedReader(new InputStreamReader(in));				
				
				String str1 = br.readLine();
				String str2 = br.readLine();				
								
				if(totals.get(str1)==null)
				{
					totals.put(str1,"0");
				} 					
				
				if (str2.equals("reset"))
				{
					sum = 0;
					System.out.println("reset done");						
				}
				else 
				{				
					x = Integer.parseInt(str2);
					sum = Integer.parseInt(totals.get(str1)) + x ;						
				}	
					totals.put(str1,sum+"");
					pw.println(sum);
					System.out.println("........................................");
					System.out.println("Creating xml");
					Element rootElement = dom.createElement("class");
					dom.appendChild(rootElement);
					
				for(Map.Entry<String, String> entry : totals.entrySet()){
					
					Element client = dom.createElement("client");
			        rootElement.appendChild(client);			         
			        Attr attr = dom.createAttribute("id");
			        attr.setValue(entry.getKey());
			        client.setAttributeNode(attr);					 
			        Element value = dom.createElement("value");
			        value.appendChild(dom.createTextNode(entry.getValue()));
			        client.appendChild(value);
				}	
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    DOMSource source = new DOMSource(dom);
			    StreamResult result = new StreamResult(new File("xmltest.xml"));
			    transformer.transform(source, result);
			    StreamResult consoleResult = new StreamResult(System.out);
			    transformer.transform(source, consoleResult);
				
			}	
		}catch (Exception e1) {e1.printStackTrace();}		
		finally {
				try {
					if (out != null) out.close();
					if (in != null) in.close();
					if (s2 != null) s2.close();
			}catch (Exception e) {e.printStackTrace();}
		}
	}
}

