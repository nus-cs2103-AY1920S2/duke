package duke;
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
     * This method is to evaluate the input and execute respective types of commands
     * @param input This is the commandline input that user keyed in
     * @param list This is the ArrayList of Object
     */
    public static String evaluateInput(String input, ArrayList<Task> list) throws IOException {

        String output ="Sorry, me stupid. Could you type it again? ";
        if(input.equals("bye")){
            output ="Bye bye, Master! Please come back soon!";
        }
        if(input.equals("list")){
            output = TaskList.printList(list);
        }
        if(input.contains("done")){
            output = TaskList.markDone(list, input);
        }
        if(input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            output = TaskList.createObject(input, list);
        }
        if(input.contains("delete")){
            output = TaskList.deleteTask(list,input);
        }

        if(input.equals("clear")){
            output = "Sir, are you sure about it? Please enter yes or no" ;
        }

        if(input.equals("yes")){
            TaskList.clearList(list);
            output = "Alrighy, Sir. I have emptied the list.";
        }

        if(input.equals("no")){
            output = "Alrighty, Sir. The list remains at it is.";
        }

        if(input.contains("search")){
            output = TaskList.searchByDate(list, input);
        }

        if(input.contains("find")){
            output = TaskList.searchByName(list,input);
        }


        /*if(!input.equals("bye")){
            Ui.readInput(list);
        }
        */

        return output;
    }
}
