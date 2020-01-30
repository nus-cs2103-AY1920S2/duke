package Utils;

import java.util.Scanner;

public class Ui {

    public Scanner sc;

    public Ui(){
        this.sc = new Scanner(System.in);
    }

    public static void printDivider(){
        System.out.println("-------------------------------------------------------------");
    }

    public static boolean isFinished(String input){
        return input.equals("bye");
    }

    public void exit(){
        System.out.println("It was a pleasure to serve you my lord. Check in again anytime.");
    }

    public void enter(){
        System.out.println("Good day master. How may I be of service today?");
    }
}
