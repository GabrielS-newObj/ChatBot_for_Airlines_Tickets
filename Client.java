import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static boolean exit = false;

    //same in ChatBot
    private static final String EXIT_NOW = "EXIT_NOW";

    //same in Brain from ChatBot
    private static final String FINAL_LINE = "FINAL_LINE";



    public static void main(String[] args) {




            try {


                String buffer;
                boolean isTimeToSpeak = false;


                Scanner input = new Scanner(System.in);




                Socket socket = new Socket("localhost", 4000);

                InputStreamReader clientInput = new InputStreamReader(socket.getInputStream());

                BufferedReader clientBuffer = new BufferedReader(clientInput);

                PrintStream clientPrint = new PrintStream(socket.getOutputStream(), true);



                while(true) {



                    if (isTimeToSpeak) {
                        isTimeToSpeak = false;

                        System.out.print("you:");
                        clientPrint.println(input.nextLine());
                    }
                    else {

                        isTimeToSpeak = true;


                        buffer = clientBuffer.readLine();


                        while (buffer != null) {

                            if (buffer.equals(EXIT_NOW)) {
                                exit = true;
                                break;
                            }

                            if(buffer.endsWith(FINAL_LINE)){

                                buffer = (buffer.replace(FINAL_LINE, ""));

                                System.out.println(buffer);
                                break;
                            }

                            System.out.println(buffer);


                            buffer = clientBuffer.readLine();

                        }

                    }








                    if (exit){break;}
                }

                input.close();
                clientPrint.close();
                clientBuffer.close();
                clientInput.close();
                socket.close();





            } catch (UnknownHostException uhe) {
                System.out.println("Host not found");
            } catch (IllegalArgumentException ia) {
                System.out.println("Illegal argument");
            } catch (IOException ioe) {
                System.out.println("IO exception");
            } catch (SecurityException se) {
                System.out.println("Security exception");
            }





    }
}

