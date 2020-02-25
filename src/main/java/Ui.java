import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static Scanner sc= new Scanner(System.in);
    public Ui(){
    }

    public static void printGreetings(){
        System.out.println("Hello! Call me sexy bot! \n" + "What can I do for you, Master?");
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
