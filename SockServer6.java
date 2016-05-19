
import java.io.*;
import java.net.*;
import java.util.*;

class MultiThread implements Runnable {
	
	Socket s2;	
	Map<String, String> totals = null;
	int sleepval;
	
	private static final Object lock = new Object();
	
	MultiThread(Socket s2, Map<String, String> totals1, int sleepval) {
	      this.s2 = s2;
		  this.totals = totals1;
		  this.sleepval = sleepval;
	 }
	 
	 public void run() {	
	 
			InputStream in = null;
			 OutputStream out = null;
		 
		 try {
			 int sum = 0;
			 int x = 0;			 
			 in = s2.getInputStream();
			 out = s2.getOutputStream();			
				
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out), true);					
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				String str1 = br.readLine();
				String str2 = br.readLine();
				
				
				synchronized (lock) {
					if(totals.get(str1)==null)
					{
						totals.put(str1,"0");
					}
					
					//thread sleep
					Thread.sleep(sleepval);
					
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
							
						System.out.println("Client "+str1+" running total calculation is completed");
						System.out.println(".......................................................");
						totals.put(str1,sum+"");
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

public class SockServer6 {

	public static void main(String args[]) {
		
		ServerSocket s1 = null;
		Socket s2 = null;
			
		try {
			s1 = new ServerSocket(8888);			
			System.out.println("Server started running");	

			int sleepval = Integer.parseInt(args[0]);
			
			Map<String, String> totals1 = Collections.synchronizedMap(new HashMap<String, String>());
			
			while (true) {
				
				s2 = s1.accept();
				System.out.println("Server accepted client connection request");
				new Thread(new MultiThread(s2,totals1,sleepval)).start();
				
			}				
		}catch (Exception e) {e.printStackTrace();}
		
	
	}
}
