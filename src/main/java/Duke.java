import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static HashMap<String, String> commandpairing = new HashMap<String, String>();
    public static List<Task> listOfTask = new ArrayList<Task>();
    /**
     * Perform the necessary action based on the input.
     * @param s1 String to be executed on
     * @param comm instance of the enum class
     */
    public static void operate(String s1, DukeCommand comm) {
        comm.execute(s1);
    }

    /**
     * Add commands available to user.
     */
    public static void initialize() {
        commandpairing.put("list", "1");
        commandpairing.put("done", "2");
        commandpairing.put("todo", "2");
        commandpairing.put("event", "/at");
        commandpairing.put("deadline", "/by");
        commandpairing.put("delete", "2");

        try {
            readFromFile();
        } catch (IOException readIOEx) {
            System.out.println(readIOEx.getMessage());
        }
    }

    public static void readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(
                "C:\\Users\\SOHNB101\\Documents\\myduke\\duke\\data\\duke.txt"));

        String currentline = reader.readLine();

        while (currentline != null) {
            String[] arr = currentline.split(" ", 3);

            String expression = arr[0];

            switch (expression) {
                case "[E]" :
                    listOfTask.add(new Event(arr[1], arr[2]));
                    break;
                case "[D]" :
                    listOfTask.add(new Deadline(arr[1], arr[2]));
                    break;
                case "[T]" :
                    listOfTask.add(new Todo(arr[1]));
                    break;
            }

            currentline = reader.readLine();
        }
        reader.close();
    }
    public static void writeToFile() throws IOException {
        String line = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "C:\\Users\\SOHNB101\\Documents\\myduke\\duke\\data\\duke.txt"));
        for (Task task : listOfTask) {
            if (task.getType().equals("[T]")) {
                line = "[T] " + task.getDesc();
            } else {
                line = task.getType() + " " + task.getDesc() + " " + task.getDate();
            }
            writer.write(line);
            writer.newLine();
            line = "";
        }
        writer.close();
    }
    public static void HorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greets user.
     */
    public static void greeting(){
        HorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        HorizontalLine();
    }

    /**
     * Exits the code.
     */
    public static void exit(){
        HorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        HorizontalLine();
    }

    /**
     * Find out the type of command and execute it.
     * @param input The line input by the user
     */
    public static void execcommand(String input) {
        String[] arguments = input.split(" ");

        operate(input, DukeCommand.valueOf(arguments[0].toUpperCase()));
    }

    /**
     * Adds Task (Todo, Event, Deadline) to the list.
     * @param mytask The Task to be added
     */
    public static void add(Task mytask) {
        HorizontalLine();
        System.out.println("Got it. I've added this task:");
        listOfTask.add(mytask);
        System.out.println(mytask);
        System.out.println("Now you have " + listOfTask.size() + " tasks in the list.");
        HorizontalLine();
    }

    public static boolean inputhandling(String input) {
        String[] arguments = input.split(" ");

        try {
            if (!commandpairing.containsKey((arguments[0]).toLowerCase()))
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

        } catch (DukeException ex){
            System.out.println(ex.getMessage());
            return false;
        }

        // Execute the error-checking and return true if there is no error
        // with the command
        boolean validInput = DukeEnumExceptions.valueOf(arguments[0].toUpperCase())
                .checkerror(input, commandpairing.get(arguments[0]));

        return validInput;
    }

    /**
     * The main method of the class Duke.
     * @param args Unused
     **/
    public static void main(String[] args) {

        // Initialize the Duke commands
        initialize();
        // Greet the user
        greeting();

        Scanner sc = new Scanner(System.in);

        // Exits when the user types 'bye'
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            // Check for any errors in the command
            // execute command if no errors
            if (inputhandling(line))
                // Assumption : To add things into the list, the user has to
                // type a [command][description]. For example, to add
                // "read book" into the list, the user has to type
                // "todo read book" instead of just typing "read book"
                // as typing "read book" will cause the code to throw an exception
                execcommand(line);
            // Wait for command
            line = sc.nextLine();
        }

        // Say 'bye' to the user
        exit();
        try {
            writeToFile();
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }
}
