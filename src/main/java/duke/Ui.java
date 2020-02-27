package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static Scanner sc= new Scanner(System.in);
    public Ui(){
    }

    /**
     * This method is to print greetings to the user.
     */
    public static String printGreetings(){
        return ("Hello! Call me sexy bot! \n" + "What can I do for you, Master?");
    }

    /**
     * This method is to read the input and run evaluateInput method
     * @param list This is the ArrayList of Task Objects
     */
    public static void readInput(ArrayList<Task> list) throws IOException {
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        Parser.evaluateInput(input, list);
    }
}
