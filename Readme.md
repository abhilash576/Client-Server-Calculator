Notes:
-------
In all the programs the server is listening on port number : "8888" and servername is "localhost".
Please send <ctrl+c> to close the server.

----------------------------------------------------------------------------------------------------------------

1. In this program the client sends a single integer and the stateful server replies the running total.
   
 Steps to compile and run the server program		Steps to compile and run the client program
 -------------------------------------------		--------------------------------------------
javac SockServer1.java					  	javac SockClient1.java
java SockServer1						java SockClient1 <input integer>

	
2. In this program the client can send either a "integer" or "reset" keyword.
When the client sends "reset" in command line , the server resets the running total to "0".
   
Steps to compile and run the server program		Steps to compile and run the client program
--------------------------------------------		--------------------------------------------
javac SockServer2.java					javac SockClient2.java
java SockServer2					java SockClient2 <input integer> or java SockClient2 reset
	
3. In this program the client have to send 2 arguments one <client-id> and other could be a "integer" or "reset" keyword.
When the client sends "reset" in command line , the server resets the running total to "0" of that particular <client-id>.
   
Steps to compile and run the server program		Steps to compile and run the client program
--------------------------------------------		--------------------------------------------
javac SockServer3.java					javac SockClient3.java
java SockServer3					java SockClient3 id1 100 or java SockClient3 id1 reset
	
4. In this program the client encoding problem has been resolved.
The client can send max integer value of "2,147,483,647".
When the client sends "reset" in command line , the server resets the running total to "0" of that particular <client-id>.
   
Steps to compile and run the server program		Steps to compile and run the client program
--------------------------------------------		--------------------------------------------
javac SockServer4.java					javac SockClient4.java
java SockServer4					java SockClient4 id1 100 or java SockClient4 id1 reset
	
5. In this program the state of server is saved in a xml file.
   If "new" keyword is passed as an argument to server it will create a new xml file(storing internal server state) and save it in the same path from where the program is executed.
   And if "xml" keyword is passed as an argument , the program will read the already created xml file and restore the server state.
   The xml filename used in the code is : "xmltest"
   
	   
Steps to compile and run the server program		Steps to compile and run the client program
--------------------------------------------		--------------------------------------------
javac SockServer5.java					javac SockClient5.java
java SockServer5 new or java SockServer5 xml		java SockClient5 id1 100 or java SockClient5 id1 reset
	
6. In this program , threads are created for each client connection.
Thread-safety is achieved by implementing synchronized hash-map and synchronized block in the run method.
The artificial delay (thread sleep) is put inside the synchronized block.
Please pass the time delay for thread sleep as an argument to server program as mentioned below.	
   
	   
Steps to compile and run the server program		Steps to compile and run the client program
--------------------------------------------		--------------------------------------------
javac SockServer6.java					javac SockClient6.java
java SockServer6 <time-delay>				java SockClient6 id1 100 or java SockClient6 id1 reset
	
	
