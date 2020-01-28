package core;

import java.util.Scanner;

/**
 * Platform to interact with the user.
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructor to initialize the scanner.
     */
    public Ui(){
        sc=new Scanner(System.in);
    }

    /**
     * Get the user input.
     * @return the user input in string.
     */
    public String getInput(){
        return sc.nextLine();
    }

    /**
     * Display the welcome message to the user.
     */
    public void preLog(){
        display(UiMessage.GREETING.getMsg());
    }

    /**
     * Display the exiting message to the user.
     */
    public void endLog(){
        display(UiMessage.FAREWELL.getMsg());
    }

    /**
     * Display the error message to the user.
     * @param error is the error message.
     */
    public void errorLog(String error){
        display(error);
    }

    /**
     * Display text to the user under the standard format.
     * @param msg is the text to be displayed.
     */
    public void display(String... msg){
        System.out.println("\t________________________________________________________________________________________________________________________");
        for(String str:msg) {
            System.out.println("\t" + str);
        }
        System.out.println("\t________________________________________________________________________________________________________________________");
    }

}
