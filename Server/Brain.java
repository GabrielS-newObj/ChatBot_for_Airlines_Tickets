package Server;

import java.io.PrintStream;
import java.util.Scanner;


public class Brain {





    public static PrintStream serverPrint;

    //same in Client
    private static final String FINAL_LINE = "FINAL_LINE";
    private static final String ATTENDANT = ChatBot.ATTENDANT+": ";

    private static boolean inExitYet = false;
    private static boolean inPricesTableYet = false;
    private static boolean inInitialYet = false;
    private static boolean inDontKnowYet = false;



    private static final PricesTable[] PRICES_TABLES =
    {new PricesTable("Sao Paulo","12:00pm",1200.00),
     new PricesTable("New York","02:00am",300.00),
     new PricesTable("Buenos Aires","08:00pm",900.00),
     new PricesTable("Berlin","10:00pm",2000.00),
     new PricesTable("Vienna","06:00am",1000.00),
     new PricesTable("Paris","02:00pm",1200.00),
     new PricesTable("Nassau","12:00am",700.00),
     new PricesTable("Ottawa","06:00am",300.00)
    };


    private static Scanner input = new Scanner(System.in);


    private static String travel;




    public static void processBuffer(String buffer) {


        if(
        buffer.contains("exit") ||
        buffer.contains("get out") ||
        buffer.contains("go out") ||
        buffer.contains("don't continue") ||
        (inExitYet && (buffer.contains("yes") || buffer.contains("exactly") || buffer.contains("sure"))))
        {
            exit();
        }
        else if (
        buffer.contains("price") ||
        buffer.contains("value") ||
        inPricesTableYet)
        {
            travel = buffer;
            pricesTable();
        }
        else if (
        buffer.contains("hello") ||
        buffer.contains("hey") ||
        buffer.contains("what's up") ||
        buffer.contains("hi") ||
        (inInitialYet && (buffer.contains("yes") || buffer.contains("sure") || buffer.contains("I'm") || buffer.contains("fine"))))
        {
            initial();
        }
        else if (
        buffer.contains("how are you") ||
        buffer.contains("fine?") ||
        buffer.contains("are you ok?") ||
        buffer.contains("and you?"))
        {
            greeting();
        }
        else
        {
            dontKnow();
        }


    }





    //----------------------------------------------------------



    private static void initial(){

        if (!inInitialYet) {
            inInitialYet = true;
            serverPrint.println(ATTENDANT + "It's everything okay?" + FINAL_LINE);
        }
        else{
            inInitialYet = false;
            serverPrint.println(ATTENDANT + "Good!" + FINAL_LINE);
        }
    }



    //----------------------------------------------------


    private static void greeting(){
        serverPrint.println(ATTENDANT+"I'm right!");

        try{
            Thread.sleep(2000);
        }catch (InterruptedException ie){
            System.out.println("thread interrupted while sleep");
        }

        serverPrint.println(ATTENDANT+"What should be the next steps?"+FINAL_LINE);
    }




    //----------------------------------------------------


    private static void exit(){

        if (!inExitYet) {
            inExitYet = true;
            serverPrint.println(ATTENDANT + "Maybe I understand wrong, but do you suppose want stop chatting?" + FINAL_LINE);
        }
        else{
            inExitYet = false;

            serverPrint.println(ATTENDANT + "So it's here we leave...\n");

            try{
                Thread.sleep(1300);
            }catch (InterruptedException ie){
                System.out.println("thread interrupted while sleep");
            }

            serverPrint.println(ATTENDANT + "Ask me if you need help!");

            serverPrint.print(ChatBot.EXIT_NOW);

            input.close();
            serverPrint.close();
        }

    }





    //----------------------------------------------------



    private static void pricesTable2(){

        inPricesTableYet = true;

        serverPrint.println(ATTENDANT + "If is the prices of travel you desire, waiting a minute please.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            System.out.println("thread interrupted while sleep");
        }

        serverPrint.println(ATTENDANT);
        serverPrint.println(PricesTable.toMsgPriceTable);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            System.out.println("thread interrupted while sleep");
        }

        serverPrint.println(" ");
        serverPrint.println(ATTENDANT + "Where we go :) ? (city only)"+FINAL_LINE);
    }




    //----------------------------------------------------




    private static void pricesTable3(){

        boolean findTravel = false;

        for(PricesTable price : PRICES_TABLES) {
            if (price.getCity().equalsIgnoreCase(travel)) {
                findTravel = true;
                inPricesTableYet = false;
                break;
            }
        }


        if (findTravel) {
            serverPrint.println(ATTENDANT+"GREAT CHOICE, MY FRIEND!");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                System.out.println("thread interrupted while sleep");
            }

            serverPrint.println(ATTENDANT+"I will mark here your name, buy the ticket for 1 (we have only 1 anyway) and send message to you in soon.");

            try {
                Thread.sleep(2100);
            } catch (InterruptedException ie) {
                System.out.println("thread interrupted while sleep");
            }


            serverPrint.println(ATTENDANT+"Thank you!"+FINAL_LINE);

        }
        else{
            serverPrint.println(ATTENDANT+"Sorry but I don't recognize the city because the full name is necessary!");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException ie) {
                System.out.println("thread interrupted while sleep");
            }

            serverPrint.println(ATTENDANT+"I have too much information here and I didn't sleep well LOL."+FINAL_LINE);
        }
    }





    //----------------------------------------------------






    private static void pricesTable(){

        if(!inPricesTableYet) {
            pricesTable2();
        }
        else {
            pricesTable3();
        }
    }



    //----------------------------------------------------


    private static void dontKnow(){

        if(!inDontKnowYet) {
            inDontKnowYet = true;
            serverPrint.println(ATTENDANT + "Sorry, but I don't know what to do with this short information!");
            serverPrint.println(ATTENDANT + "Can you repeat, please?" + FINAL_LINE);
        }
        else{
            inDontKnowYet = false;
            serverPrint.println(ATTENDANT + "Nice ;)" + FINAL_LINE);
        }
    }





}
