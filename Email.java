

/*
*  TCP Client Program
*  Connects to a TCP Server
*  Waits for a Welcome message from the server
*  Receives a line of input from the keyboard and sends it to the server
*  Receives a response from the server and displays it.
*  Receives a second line of input from the keyboard and sends it to the server
*  Receives a second response from the server and displays it.
*  Closes the socket and exits
*
*  @author: Michael Fahy
*  Email:  fahy@chapman.edu
*  Date:  2/4/2018
*  @version: 3.0
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class Email  {

  public static void main(String[] argv) throws Exception {

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
   

    System.out.println("Please enter the recipients email address");
    final String toAddress = inFromUser.readLine();
    System.out.println("Please enter your email address");
    final String fromAddress  = inFromUser.readLine();
    System.out.println("Please enter the recipients name address");
    final String toName = inFromUser.readLine();
    System.out.println("Please enter your name address");
    final String fromName = inFromUser.readLine();
    System.out.println("Please enter the subject of the email");
    final String subject = inFromUser.readLine();
    
                   
    System.out.println("Please enter the message of the email");
    String message = inFromUser.readLine();   
    String messageTwo = "";    
    while (messageTwo.charAt(0) != '.') {
      messageTwo = inFromUser.readLine();
      message = message + messageTwo;
    }
 
    Socket clientSocket = null;

    try {
      clientSocket = new Socket("smtp.chapman.edu", 25);
    } catch (Exception e) {
      System.out.println("Failed to open socket connection");
      System.exit(0);
    }
    PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
    BufferedReader inFromServer =  new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream()));

    String serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);

    System.out.println("HELO icd.chapman.edu");
    outToServer.println("HELO icd.chapman.edu");
    serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);
   
    System.out.println("MAIL FROM:" + fromAddress);
    outToServer.println("MAIL FROM:" + fromAddress);
    serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);
    
    System.out.println("RCPT TO:" + toAddress);
    outToServer.println("RCPT TO:" + toAddress);
    serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);
    
    System.out.println("DATA");
    outToServer.println("DATA");
    serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);


    System.out.println("From:" + fromName); 
    outToServer.println("From:" + fromName);
   
    System.out.println("To:" + toName);
    outToServer.println("To:" + toName);

    System.out.println("Subject:" + subject);
    outToServer.println("Subject:" + subject);
    
    System.out.println("Message:" + message);
    outToServer.println("Message:" + message);
    serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);


    System.out.println("QUIT");
    outToServer.println("QUIT");
    serverMessage = inFromServer.readLine();
    System.out.println("FROM SERVER:" + serverMessage);



    clientSocket.close();
  }
}
