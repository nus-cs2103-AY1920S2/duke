import java.util.Scanner;
import java.util.HashMap;

public class Duke {
    public static int count = 0;
    public static Task[] listOfTask = new Task[100];
    public static HashMap<String, String> commandpairing = new HashMap<String, String>();

    /**
     * Perform the necessary action based on the input.
     * @param s1
     * @param comm
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
        System.out.println("");
        HorizontalLine();
    }

    /**
     * Find out the type of command and execute it.
     * @param input
     */
    public static void execcommand(String input) {
        String[] arguments = input.split(" ");

        if (commandpairing.containsKey(arguments[0])) {
            //Either list or done command
            operate(input, DukeCommand.valueOf(arguments[0].toUpperCase()));
        } else {
            // Add command
            add(input);
        }

    }

    /**
     * Adds the task into the list.
     * @param line
     */
    public static void add(String line) {
        HorizontalLine();
        listOfTask[count++] = new Task(line);
        System.out.println("added: " + line);
        HorizontalLine();
    }

    /**
     * The main driver class Duke.
     * @param args
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
