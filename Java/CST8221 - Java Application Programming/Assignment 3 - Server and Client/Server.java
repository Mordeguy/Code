/*FILE HEADER -------------------------------------------------------------------------------------------------------
          File Name:  Server.java
             Author:  Nathan M. Abbey, 040-557-192
             Course:  CST8221 - Java Application Programming
        Lab Section:  301
  Assignment Number:  02 pt. 2
               Date:  January 4, 2018
          Professor:  Svillen Ranev
            Purpose:  This program creates a server that a client can connect to. The server responds to commands
                      input by the client and displays messages.
                      object and begin.
         Class List:  1. Server <--
         			  2. ServerSocketRunnable
 -------------------------------------------------------------------------------------------------------------------*/
/**
 * This class creates a ServerSocket object with a port indicated at command-line execution. Otherwise, the port
 * defaults to 65535. A socket is created when a client connects to the server. A ServerSocketRunnable and Thread
 * are created and handle the user input.
 *   
 * @author Nathan M. Abbey
 * @version 1.0
 * @see ServerSocketRunnable.java
 * @since 1.8_066
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	/*Default port number if none is selected at command line execution*/
	private static final int DEFAULT_PORT = 65535;
		
	public static void main(String[] args) {

		/*This code deals with he command line arguments and setting a port*/
		String commandLine;
		int port;

		if( args.length > 0 ) {
			commandLine = args[0];
			port = Integer.parseInt(commandLine);
			System.out.println("Using port:  " + port);
		} else {
			port = DEFAULT_PORT;
			System.out.println("Using default port:  " + DEFAULT_PORT);
		}
		
		try {
			/*This creates a new ServerSocket using the port decided above*/
			ServerSocket s = new ServerSocket(port);
			
	         while (true) {  
	        	 
	        	 /*A user connects and a ServerSocketRunnable and Thread handle user interaction*/
	            Socket incoming = s.accept();
	            InetAddress localHostAddress = InetAddress.getLocalHost();
	            System.out.println("Connecting to a client Socket[addr=/" + localHostAddress + ",port=" + incoming.getLocalPort() + ",localport=" + incoming.getPort() );
	            
	            //
	            ServerSocketRunnable r = new ServerSocketRunnable(incoming);
	            Thread t = new Thread(r);
	            t.start();
	         }
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error Connecting. See above message for more information.");
		}
	}
}
