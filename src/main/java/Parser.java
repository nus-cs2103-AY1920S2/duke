import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with making sense of user command
 */
public class Parser {

    /**
     * This method is to read the input and run evaluateInput method
     * @param list This is the ArrayList of Task Objects
     */
    public static void readInput(ArrayList<Task>list) throws IOException {
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        evaluateInput(input, list);
    }

    /**
     * This method is to evaluate the input and execute respective types of command
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Object
     */
    public static void evaluateInput(String input, ArrayList<Task> list) throws IOException {

        if(input.equals("bye")){
            System.out.println( "Bye bye, Master! Please come back soon!");
        }
        if(input.equals("list")){
            TaskList.printList(list);
        }
        if(input.contains("done")){
            TaskList.markDone(list, input);
        }
        if(input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            TaskList.createObject(input, list);
        }
        if(input.contains("delete")){
            TaskList.deleteTask(list,input);
        }

        if(input.contains("search")){
            TaskList.searchByDate(list, input);
        }
        if(!input.equals("bye")){
            Ui.readInput(list);
        }
    }
}
