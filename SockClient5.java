
import java.net.*;
import java.io.*;

public class SockClient5 {
	
	public static void main(String args[]) {
		
		Socket s1 = null ;
		InputStream in = null;
		OutputStream out = null;
				
		try {
			//Client sending connection request to server
			s1 = new Socket("localhost",8888);
			
			in = s1.getInputStream();
			out = s1.getOutputStream();
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));		
			
			pw.println(args[0]);
			pw.println(args[1]);
					
			int sum = Integer.parseInt(br.readLine());		
			
			System.out.println("Sum operation result for client id:"+" "+args[0]+" is"+ " "+ sum);		
		
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
