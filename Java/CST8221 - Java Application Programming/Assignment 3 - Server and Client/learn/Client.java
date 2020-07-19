package learn;
/*FILE HEADER -------------------------------------------------------------------------------------------------------
          File Name:  Client.java
             Author:  Nathan M. Abbey, 040-557-192
             Course:  CST8221 - Java Application Programming
        Lab Section:  301
  Assignment Number:  02 pt. 2
               Date:  January 4, 2018
          Professor:  Svillen Ranev
            Purpose:  This program creates a GUI client that can connect to a server. The server responds to commands
                      input by the client and displays messages.
                      object and begin.
         Class List:  1. Client <--
                      2. ClientView
                      3. SocketConnect (inner class)
 -------------------------------------------------------------------------------------------------------------------*/
/**
 * This class is the "driver" class for the GUI. It initializes a GUI object and displays it to the user.
 *   
 * @author Nathan M. Abbey
 * @version 1.0
 * @see Client.java, SocketConnect.java
 * @since 1.8_066
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
   

        java.awt.EventQueue.invokeLater(new Runnable() {

            /*Sets the GUI so it appears for the user to interact with*/
            public void run() {
                /*Creates a new GUI Object*/
                ClientView cV = new ClientView();
                cV.setVisible(true);
            }
        });
    }
}


