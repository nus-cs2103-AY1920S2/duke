import java.util.Scanner;

/**
 * Represents an object that handles user input and interaction through prompts such as welcome and farewell messages,
 * as well as indicating success of task addition, deletion and flagging to the user
 * when an input of the wrong format is entered.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() {
        // Read user input
        return this.sc.nextLine();
    }

    /**
     * Prints a greeting message to the user.
     */
    public void sayHello() {
        // Print welcome message
        this.printLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        this.printLine();
    }

    /**
     * Prints a farewell message to the user.
     */
    public void sayBye() {
        // Print goodbye message
        this.printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        this.printLine();
        System.out.println();
    }


    /**
     * Alerts user of a successful task addition to their task list.
     * @param task Task object that was added.
     * @param taskListSize Size of the user's task list after addition.
     */
    public void printTaskAddSuccess(Task task, int taskListSize) {
        this.printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskListSize + " tasks in the list.");
        this.printLine();
    }

    /**
     * Alerts the user of a successful task deletion from their task list.
     * @param task Task object that was deleted.
     * @param taskListSize Size of the user's task list after deletion.
     */
    public void printTaskDeleteSuccess(Task task, int taskListSize) {
        this.printLine();
        System.out.println("\t Understood. I've deleted this task:");
        System.out.println("\t " + task);
        System.out.println("\t Now you have " + taskListSize + " tasks in the list.");
        this.printLine();
    }

    /**
     * Alerts the user of successfully marking a task as done,
     * and showing the user the task that was marked.
     * @param task Task object that was marked as done.
     */
    public void printMarkDoneSuccess(Task task) {
        this.printLine();
        System.out.println("\t Alright! I've marked this task as done:");
        System.out.println("\t " + task);
        this.printLine();
    }

    /**
     * Alerts the user of an incorrect command input.
     */
    public void flagWrongCommand() {
        this.printLine();
        String sorryStr = "\t Sorry! You've entered a wrong command, please try again!\n";
        String helpStr = "\t List of commands: \n" + "\t  todo\n" + "\t  event\n"
                + "\t  deadline\n" + "\t  list\n";
        System.out.println(sorryStr + helpStr);
        this.printLine();
    }


    /**
     * Helper function to print separator lines.
     */
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}
