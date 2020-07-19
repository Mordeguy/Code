/*FILE HEADER -------------------------------------------------------------------------------------------------------
          File Name:  ServerSocketRunnable.java
             Author:  Nathan M. Abbey, 040-557-192
             Course:  CST8221 - Java Application Programming
        Lab Section:  301
  Assignment Number:  02 pt. 2
               Date:  January 4, 2018
          Professor:  Svillen Ranev
            Purpose:  This program creates a server that a client can connect to. The server responds to commands
                      input by the client and displays messages.
                      object and begin.
         Class List:  1. Server 
         			  2. ServerSocketRunnable <--
 -------------------------------------------------------------------------------------------------------------------*/
/**
 * This class accepts a Socket as a parameter and responds to client commands by displaying messages when
 * appropriate.
 *   
 * @author Nathan M. Abbey
 * @version 1.0
 * @see ServerSocketRunnable.java
 * @since 1.8_066
*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerSocketRunnable implements Runnable {

	/*The socket used to initialize the incoming Socket parameter in the constructor*/
	Socket s = null;

	/*Constructor*/
	public ServerSocketRunnable(Socket s){
		this.s = s;
	}

	@Override
	public void run() {

		/*These are both used to manage input and output; providing functionality*/
		ObjectOutputStream oOS;
		ObjectInputStream oIS;

		try {  
			try { 
				/*Initialize both "Stream" objects to the Sockets appropriate streams*/
				oOS = new ObjectOutputStream(s.getOutputStream());
				oIS = new ObjectInputStream(s.getInputStream());

				/*When the above is successful send this message out to the client to indicate success*/
				oOS.writeObject("Connected to Socket[addr=" + s.getInetAddress() + ",port=" +  s.getLocalPort() + ",localport=" + s.getPort() + "]" );

				boolean done = false;

				/*This loops takes in a client command and determines what to display depending on input command*/
				while (!done) {  
					String line = "";
					try {
						try {
							/*Takes in a command from the client Socket*/
							line = (String)oIS.readObject();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					} catch (IOException e) {
						break;
					}            

					/*Displays the time using Java DateFormat in the java library*/
					if (line.equals("-time")){
						DateFormat dateFormat = new SimpleDateFormat(" hh:mm:ss aa");
						String dateString = dateFormat.format(new Date()).toString();
						oOS.writeObject("SERVER>TIME:  " + dateString);

					/*Displays the date using Java DateFormat in the java library*/	
					}else if (line.equals("-date")){
						DateFormat dF = new SimpleDateFormat("dd MMMM yyyy");
						String dateString2 = dF.format(new Date()).toString();
						oOS.writeObject("SERVER>DATE: " + dateString2); 

					/*Echos back user input if the start of the command is '-echo'*/
					} else if (line.length() >= 6 && line.substring(0, 6).equals("-echo-")){   
						String subLine = line.substring(6, line.length());
						oOS.writeObject("ECHO: " + subLine);

					/*Displays all available commands*/
					} else if (line.matches("-help")){
						String s1 = "SERVER>Available Services:\nquit\necho\ntime\ndate\nhelp\nclrs\n\n";
						oOS.writeObject(s1);

					/*Quits if the user indicates and breaks out of the WHILE loop*/
					} else if (line.trim().equals("-quit")){
						oOS.writeObject("SERVER> Connection closed.");
						done = true;
					
					/*If no command is recognized, the displays prints out a blank line*/
					} else 
						oOS.writeObject(" ");     	  
				}
			}
			finally {
				
				/*Closes the Socket*/
				s.close();
			}
		}
		catch (IOException e) {  
			e.printStackTrace();
		}
	}
}