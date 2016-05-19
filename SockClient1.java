
import java.net.*;
import java.io.*;

public class SockClient1 {
	
	public static void main(String args[]) {
		
		Socket s1 = null ;
		InputStream in = null;
		OutputStream out = null;		 
		
		try {
			
			//Client sending connection request to server
			s1 = new Socket("localhost",8888);
			
			in = s1.getInputStream();
			out = s1.getOutputStream();
			
			int a = Integer.parseInt(args[0]);
			
			out.write(a);			
			
			int sum = in.read();
			
			System.out.println("Sum operation result is"+ " "+ sum);
		
		
		} catch(Exception e) {e.printStackTrace();}
		
		finally {
			try {			
				if (out != null) out.close();
				if (in != null) in.close();
				if (s1 != null) s1.close();
			}catch (Exception e) {e.printStackTrace();}
		}		
	}
}
