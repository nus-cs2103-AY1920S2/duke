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
        String header = DukeFormat.indent("Hello from\n" + DukeFormat.LOGO);
        System.out.println(header);
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

        // Keep waiting for user input until the exit command is entered
        while (!exit && sc.hasNext()) {
            String input = sc.next();
            System.out.println();

            try {
                // Handle different commands
                switch (input) {
                case "bye":
                    checkOneWordCommand(sc, "bye"); // Exception if command is not one word
                    exit = true;
                    break;
                case "deadline":
                    taskList = add(taskList, inputDeadline(sc));
                    break;
                case "delete":
                    taskList = delete(taskList, sc);
                    break;
                case "done":
                    taskList = done(taskList, sc);
                    break;
                case "event":
                    taskList = add(taskList, inputEvent(sc));
                    break;
                case "list":
                    checkOneWordCommand(sc, "list");
                    list(taskList);
                    break;
                case "todo":
                    taskList = add(taskList, inputTodo(sc));
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException e) {
                // Output exception message
                echo(e.getMessage());
            }
        }
    }

    /**
     * Outputs the given message into the chat-bot. The message will be
     * sandwiched between two lines and indented.
     *
     * @param message the text to output into the chat-bot
     */
    public static void echo(String message) {
        System.out.println(DukeFormat.indentedLine());

        System.out.println(DukeFormat.indent(message + "\n", 5));

        System.out.println(DukeFormat.indentedLine());
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
     * @param task the task to add to the list.
     * @return a copy of the TaskList with the newly added task.
     */
    public static TaskList add(TaskList taskList, Task task) {
        TaskList newList = taskList.addTask(task);

        String confirmation = "Okie! I've added this task:\n";
        String count = "There are now " + newList.getNumTasks() + " tasks in the list.";

        echo(confirmation + DukeFormat.indent(task.toString(), 2) + "\n" + count);

        return newList;
    }

    /**
     * Creates a new To-do from user input.
     *
     * @param sc the scanner for user input.
     * @return a new To-do from user input.
     * @throws DukeException if user input is blank.
     */
    public static Todo inputTodo(Scanner sc) throws DukeException {
        String input = sc.nextLine().strip();

        // Check input is not blank
        if (!input.isEmpty()) {
            return new Todo(input);
        } else {
            // Throw exception for blank inputs
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Creates a new Deadline from user input.
     *
     * @param sc the scanner for user input.
     * @return a new Deadline from user input.
     * @throws DukeException if user input is blank.
     * @throws DukeException if user input has incorrect format.
     */
    public static Deadline inputDeadline(Scanner sc) throws DukeException {
        String delimiter = " /by ";

        String input = sc.nextLine().strip();

        // Check input is not blank
        if (!input.isEmpty()) {
            int delimiterIndex = input.indexOf(delimiter);

            // Check input format
            if (delimiterIndex >= 0) {
                String description = input.substring(0, delimiterIndex)
                        .strip();

                String by = input.substring(delimiterIndex + delimiter.length())
                        .strip();

                return new Deadline(description, by);

            } else {
                // Throw exception for incorrect inputs
                throw new DukeException("☹ OOPS!!!"
                        + " To input a deadline, please enter:\n"
                        + "    a non-empty description, followed by\n"
                        + "    the keyword /by, then\n"
                        + "    the cut-off date/time for the deadline.");
            }
        } else {
            // Throw exception for blank inputs
            throw new DukeException("☹ OOPS!!! You didn't input anything. Please try again.");
        }
    }

    /**
     * Creates a new Event from user input.
     *
     * @param sc the scanner for user input.
     * @return a new Event from user input.
     * @throws DukeException if user input is blank.
     * @throws DukeException if user input has incorrect format.
     */
    public static Event inputEvent(Scanner sc) throws DukeException {
        String delimiter = " /at ";

        String input = sc.nextLine().strip();

        // Check input is not blank
        if (!input.isEmpty()) {
            int delimiterIndex = input.indexOf(delimiter);

            // Check input format
            if (delimiterIndex >= 0) {
                String description = input.substring(0, delimiterIndex)
                        .strip();

                String otherDetails = input.substring(delimiterIndex + delimiter.length())
                        .strip();

                int lastSpace = otherDetails.lastIndexOf(" ");

                // Check input format again
                if (lastSpace >= 0) {
                    String at = otherDetails.substring(0, lastSpace).strip();
                    String timeSlot = otherDetails.substring(lastSpace + 1).strip();

                    return new Event(description, at, timeSlot);
                } else {
                    // Throw exception for blank inputs
                    throw new DukeException("☹ OOPS!!! The time range of the event cannot be empty.");
                }

            } else {
                // Throw exception for incorrect inputs
                throw new DukeException("☹ OOPS!!!"
                        + " To input an event, please enter:\n"
                        + "    a non-empty description, followed by\n"
                        + "    the keyword /at, then\n"
                        + "    the date of the event, and finally\n"
                        + "    the start-end time as one word, in time-time format.");
            }
        } else {
            // Throw exception for blank inputs
            throw new DukeException("☹ OOPS!!! You didn't input anything. Please try again.");
        }
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
     * @param sc the scanner accepting user input.
     * @return a copy of the TaskList with the specified task marked as done.
     * @throws DukeException if next input is not a single integer.
     * @throws DukeException if next input is not a valid task number.
     */
    public static TaskList done(TaskList taskList, Scanner sc) throws DukeException {
        try {
            // Check if the next input is a number
            String remainingInput = sc.nextLine().strip();

            // Convert remaining input to an integer if possible
            // Throws a NumberFormatException if input otherwise
            int taskId = Integer.parseInt(remainingInput);

            // Check if task number is valid
            if (0 < taskId && taskId <= taskList.getNumTasks()) {
                String praise = "Good job completing this task!"
                        + " Here's a tick for completing it!\n";

                TaskList newList = taskList.doneTask(taskId);
                echo(praise + DukeFormat.indent(newList.get(taskId).toString(), 2));


                return newList;

            } else {
                // Invalid task number
                throw new DukeException("☹ OOPS!!! Your task number does not exist.");
            }
        } catch (NumberFormatException e) {
            // Incorrect input format
            throw new DukeException("☹ OOPS!!! Please ensure your input is a single integer.");
        }
    }

    /**
     * Removes a task from the list of tasks in the chat-bot.
     *
     * @param taskList the list of tasks.
     * @param sc the scanner accepting user input.
     * @return a copy of the TaskList with the specified task removed.
     * @throws DukeException if next input is not a single integer.
     * @throws DukeException if next input is not a valid task number.
     */
    public static TaskList delete(TaskList taskList, Scanner sc) throws DukeException {
        try {
            // Check if the next input is a number
            String remainingInput = sc.nextLine().strip();

            // Convert remaining input to an integer if possible
            // Throws a NumberFormatException if input otherwise
            int taskId = Integer.parseInt(remainingInput);

            // Check if task number is valid
            if (0 < taskId && taskId <= taskList.getNumTasks()) {
                Task deletedTask = taskList.get(taskId);
                TaskList newList = taskList.deleteTask(taskId);

                String comment = "Alright! I'll remove this task:\n";
                String count = "There are now " + newList.getNumTasks() + " tasks in the list.";

                echo(comment + DukeFormat.indent(deletedTask.toString(), 2) + "\n" + count);

                return newList;

            } else {
                // Invalid task number
                throw new DukeException("☹ OOPS!!! Your task number does not exist.");
            }
        } catch (NumberFormatException e) {
            // Incorrect input format
            throw new DukeException("☹ OOPS!!! Please ensure your input is a single integer.");
        }
    }

    /**
     * Will validate whether a previous user input was a single word command.
     *
     * @param sc the scanner accepting user input.
     * @param command the user command for printing the correct exception
     * @throws DukeException if command contains trailing words.
     */
    private static void checkOneWordCommand(Scanner sc, String command) throws DukeException {
        String remainingInput = sc.nextLine().strip();

        // Verify command is one word
        if (!remainingInput.isEmpty()) {
            throw new DukeException("☹ OOPS!!! " + command + " is a one word command.");
        }
    }
}