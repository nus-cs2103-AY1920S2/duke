import java.util.Scanner;

public class Duke {
    /** The main method is where the chat-bot is created and executed. */
    public static void main(String[] args) {
        displayLogo();
        greet();

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        inputCommands(sc, taskList);

        exit();
    }

    /** Outputs the program's logo in the chat-bot. */
    private static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indent("Hello from\n" + logo));
    }

    /**
     * The main loop for handling user commands. Stops looping when the given
     * exit command is the next input.
     *
     * @param sc the scanner accepting user input
     * @param taskList the list of tasks.
     */
    public static void inputCommands(Scanner sc, TaskList taskList) {
        // Terminates the chat-bot if true
        boolean exit = false;

        while (!exit && sc.hasNext()) {
            String input = sc.next();
            System.out.println();

            // Handle different commands
            switch (input) {
            case "bye":
                exit = true; // Terminate the program
                break;
            case "done":
                int taskId = sc.nextInt();
                taskList = done(taskList, taskId);
                break;
            case "list":
                list(taskList);
                sc.nextLine();
                break;
            default:
                taskList = add(taskList, input + sc.nextLine());
                //echo(command); // Repeat the user's command
                break;
            }
        }
    }

    /** Adds a four-space indent to all lines of a given text. */
    private static String indent(String text) {
        return indent(text, 4);
    }

    /**
     * Adds an indent to all lines of a given text.
     *
     * @param text the text to indent.
     * @param indentWidth the character width of the indentation.
     * @return the indented text.
     */
    private static String indent(String text, int indentWidth) {
        String indent = " ".repeat(indentWidth);

        return text.replaceAll("(?m)^", indent);
    }

    /** Outputs an indented line in the chat-bot. */
    private static void drawLine() {
        String lineSymbol = "-";
        int lineWidth = 60;

        System.out.println(indent(lineSymbol.repeat(lineWidth)));
    }

    /**
     * Outputs the given message into the chat-bot. The message will be
     * sandwiched between two lines and indented.
     *
     * @param message the text to output into the chat-bot
     */
    public static void echo(String message) {
        drawLine();

        System.out.println(indent(message + "\n", 5));

        drawLine();
        System.out.println(); // New line below each chat-bot message
    }

    /** Welcomes the chat-bot user. */
    public static void greet() {
        String greeting = "Hey there! I'm Duke\n"
                + "Your word is my command!";

        echo(greeting);
    }

    /** Farewells the chat-bot user. */
    public static void exit() {
        String farewell = "Bye! Please give a review if you like this program!";

        echo(farewell);
    }

    /**
     * Adds a task into the chat-bot.
     *
     * @param taskList the list of tasks.
     * @param taskDescription the details of the new task.
     * @return a copy of the TaskList with the newly added task.
     */
    public static TaskList add(TaskList taskList, String taskDescription) {
        echo("added: " + taskDescription);

        return taskList.addTask(taskDescription);
    }

    /**
     * Lists all tasks in the chat-bot.
     *
     * @param taskList the list of tasks to display.
     */
    public static void list(TaskList taskList) {
        String title = "Here are the tasks in your list:\n";

        echo(title + taskList.toString());
    }

    /**
     * Marks a task as completed in the chat-bot.
     *
     * @param taskList the list of tasks.
     * @param taskId the number of the task in the list to be marked as done.
     * @return a copy of the TaskList with the specified task marked as done.
     */
    public static TaskList done(TaskList taskList, int taskId) {
        String praise = "Good job completing this task!"
                + " Here's a tick for completing it!\n";

        TaskList newList = taskList.doneTask(taskId);

        echo(praise + indent(newList.get(taskId).toString(), 2));

        return newList;
    }
}