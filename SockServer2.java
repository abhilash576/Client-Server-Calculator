
import java.io.*;
import java.net.*;


public class SockServer2 {

	public static void main(String args[]) {
		
		ServerSocket s1 = null;
		InputStream in = null;
		OutputStream out = null;
		Socket s2 = null;
		
		int sum = 0;
		int x = 0;
		
		try {
			s1 = new ServerSocket(8888);			
			System.out.println("Server started running ......");
		
			while (true) {
				
				s2 = s1.accept();				
				in = s2.getInputStream();
				out = s2.getOutputStream();			
				
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out), true);					
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				String str1 = br.readLine();					
				
				if (str1.equals("reset"))
				{
					sum = 0;
					System.out.println("Reset done");						
				}
				else 
				{				
					x = Integer.parseInt(str1);
					sum = sum + x ;				
				}	
				pw.println(sum);
			
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
