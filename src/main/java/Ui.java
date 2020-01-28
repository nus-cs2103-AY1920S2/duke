import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui handles tasks related to the user interface of Duke. It deals with user interaction, including getting
 * user input and showing replies from Duke.
 */
public class Ui {
    private Scanner input;

    /**
     * Constructs a Ui object. A Scanner reading in from System.in will be instantiated for user input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Shows a loading error due to Duke being unable to load in saved tasks from the file specified. Informs
     * the user that a new file will be created.
     */
    public void showLoadingError() {
        System.out.println("Error: Unable to load saved tasks from file.");
        System.out.println("New data file will be created.\n");
    }

    /**
     * Shows the Duke logo and greeting.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi, I'm Duke!");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Gets input typed by the user.
     * 
     * @return User input.
     */
    public String getInput() {
        System.out.print("> ");
        return this.input.nextLine();
    }

    /**
     * Shows the information for each task in the tasks list in the order they were added.
     *
     * @param tasks List of tasks.
     */
    public void showTasks(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        
        System.out.println("\tHere are your tasks:");

        if (list.isEmpty()) {
            System.out.println("\t<No tasks have been added>");
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.format("\t%d.%s%n", i + 1, list.get(i));
        }

        System.out.println();
    }
    
    /**
     * Shows farewell message and exit notice.
     */
    public void showFarewell() {
        System.out.println("\tHave a nice day!\n");
        System.out.println("Exiting...");
    }

    /**
     * Shows specified task from given tasks list as marked done.
     * 
     * @param taskList List of tasks.
     * @param task Task which has been marked as done.
     */
    public void showSetAsDone(TaskList taskList, Task task) {
        System.out.println("\tNoted. I have marked this task as done:");
        System.out.format("\t\t%s%n%n", task);
    }

    /**
     * Shows specified task from given tasks list as added to that list.
     * 
     * @param taskList List of tasks.
     * @param task Task which has been added to list.
     */
    public void showAddTask(TaskList taskList, Task task) {
        System.out.println("\tAlright! The following task has been added:");
        System.out.format("\t\t%s%n", task);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks");
    }

    /**
     * Shows specified task from given tasks list as being deleted from that list.
     * 
     * @param taskList List of tasks.
     * @param task Task which has been deleted from list.
     */
    public void showDeleteTask(TaskList taskList, Task task) {
        System.out.println("\tNoted. I have removed this task:");
        System.out.format("\t\t%s%n", task);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks");
    }
}
