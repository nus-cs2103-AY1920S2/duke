import java.util.Scanner;
import java.util.HashMap;

public class Duke {
    public static int count = 0;
    public static Task[] listOfTask = new Task[100];
    public static HashMap<String, String> commandpairing = new HashMap<String, String>();

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
        commandpairing.put("list", " ");
        commandpairing.put("done", " ");
        commandpairing.put("todo", " ");
        commandpairing.put("event", " ");
        commandpairing.put("deadline", " ");
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

        //if (commandpairing.containsKey(arguments[0])) {
            //Either list or done command
        operate(input, DukeCommand.valueOf(arguments[0].toUpperCase()));
        //} else {
            // Add command
        //    add(input);
        //}

    }

    /**
     * Adds Task (Todo, Event, Deadline) to the list.
     * @param mytask The Task to be added
     */
    public static void add(Task mytask) {
        HorizontalLine();
        System.out.println("Got it. I've added this task:");
        listOfTask[count++] = mytask;
        System.out.println(mytask);
        System.out.println("Now you have " + count + " tasks in the list.");
        HorizontalLine();
    }
    /*
    public static void addToList(Task mytask) {
        HorizontalLine();
        System.out.println("Got it. I've added this task:");
        listOfTask[count++] = mytask;
        System.out.println(mytask);
        System.out.println("Now you have " + count + " tasks in the list.");
        HorizontalLine();
    }
    */
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
            // Execute the input command
            execcommand(line);
            // Wait for command
            line = sc.nextLine();
        }

        // Say 'bye' to the user
        exit();
    }
}
