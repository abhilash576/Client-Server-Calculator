
import java.io.*;
import java.net.*;


public class SockServer1 {

	public static void main(String args[]) {
		
		ServerSocket s1 = null;
		InputStream in = null;
		OutputStream out = null;
		Socket s2 = null;
		
		int sum = 0;
		
		try {
			s1 = new ServerSocket(8888);
			
			System.out.println("Server started running ......");
					
			while (true) {
				
				s2 = s1.accept();			
				in = s2.getInputStream();
				out = s2.getOutputStream();			
				int x = in.read();						
				sum = sum +x;
				
				out.write(sum);
				out.flush();
			}				
		}catch (Exception e) {e.printStackTrace();} 
		finally {
				try {
					if (out != null) out.close();
					if (in != null) in.close();
					if (s2 != null) s2.close();
			}catch (Exception e) {e.printStackTrace();}
		}
				
	}
}
