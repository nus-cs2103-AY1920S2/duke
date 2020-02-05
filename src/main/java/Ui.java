import java.util.Scanner;

/**
 * Represents an object that handles user input and user interaction through prompts such as welcome and farewell messages,
 * as well as indicating success of task addition and deletion and flagging to the user when an input of the wrong format is entered.
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

    public void sayHello() {
        // Print welcome message
        this.printLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        this.printLine();
    }
    public void sayBye() {
        // Print goodbye message
        this.printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        this.printLine();
        System.out.println();
    }


    public void printTaskAddSuccess(Task task, int taskListSize) {
        this.printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskListSize + " tasks in the list.");
        this.printLine();
    }

    public void printTaskDeleteSuccess(Task task, int taskListSize) {
        this.printLine();
        System.out.println("\t Understood. I've deleted this task:");
        System.out.println("\t " + task);
        System.out.println("\t Now you have " + taskListSize + " tasks in the list.");
        this.printLine();
    }

    public void printMarkDoneSuccess(Task task) {
        this.printLine();
        System.out.println("\t Alright! I've marked this task as done:");
        System.out.println("\t " + task);
        this.printLine();
    }

    public void flagWrongCommand() {
        String sorryStr = "Sorry! You've entered a wrong command, please try again!\n";
        String helpStr = "\t List of commands: \n" + "\t  todo\n" + "\t  event\n"
                + "\t  deadline\n" + "\t  list\n";
        System.out.println(sorryStr + helpStr);
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}
