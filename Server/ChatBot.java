package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;


/*
    toLowerCase method for standard already used here
 */

public class ChatBot {


    //be used in Brain
    public static final String ATTENDANT = "Thainá";
    public static final String EXIT_NOW = "EXIT_NOW";



    public static void main(String[] args) {



            try (ServerSocket serverSocket = new ServerSocket(4000)) {



                String buffer = "";
                String msg;
                boolean flag = true;


                System.out.println("ChatBot waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("ChatBot connected!!");


                InputStreamReader serverInput = new InputStreamReader(socket.getInputStream());


                BufferedReader serverBuffer = new BufferedReader(serverInput);

                PrintStream serverPrint = new PrintStream(socket.getOutputStream(), true);




                while(true) {




                    if (flag){
                        flag = false;

                        Brain.serverPrint = serverPrint;


                        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

                        if (hour < 4) {
                            msg = "morning early";
                        }
                        else if(hour < 12){
                            msg = "morning";
                        }
                        else if(hour == 12){
                            msg = "lunch time";
                        }
                        else if(hour < 18){
                            msg = "afternoon";
                        }
                        else{
                            msg = "evening";
                        }

                            serverPrint.print("\t\t"+"_________________________________"+"\n\t\t"+"AIRLINES TICKETS SIMULATOR CHATBOT"+"\n\t\t"+"_________________________________");
                            String send = ("\n\n"+"Hello! My name is "+ATTENDANT+"\n\n"+"How can I help you in this "+msg+"?");
                            serverPrint.println(send+"FINAL_LINE");

                    }



                    buffer = serverBuffer.readLine();

                    if (buffer != null) {

                        buffer = buffer.trim().toLowerCase();

                        System.out.println("Message received: "+buffer);

                        Brain.processBuffer(buffer);

                    }




                }




            } catch (IOException e) {
                System.out.println("\n\nconnection lost...");
            }








    }

}
