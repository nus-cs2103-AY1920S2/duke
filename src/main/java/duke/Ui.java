package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui handles tasks related to the user interface of Duke. It deals with user interaction, including getting
 * user input and generating replies from Duke.
 */
public class Ui {
    private Scanner input;
    private TaskList tasks;

    /**
     * Constructs a Ui object. A Scanner reading in from System.in will be instantiated for user input.
     */
    public Ui(TaskList tasks) {
        this.input = new Scanner(System.in);
        this.tasks = tasks;
    }

    /**
     * Returns the Duke logo and greeting.
     * 
     * @return Greeting string.
     */
    public String showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return logo + "Hi, I'm Duke!\n" + "What can I do for you?";
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
     * Returns the information for each task in the tasks list in the order they were added.
     *
     * @return List of tasks.
     */
    public String showTasks() {
        ArrayList<Task> list = tasks.getList();
        StringBuilder sb = new StringBuilder();
        
        sb.append("Here are your tasks:\n");
        
        if (list.isEmpty()) {
            sb.append("<No tasks have been added>");
        }

        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d.%s%n", i + 1, list.get(i)));
        }
        
        return sb.toString();
    }
    
    /**
     * Returns farewell message and exit notice.
     * 
     * @return Farewell message and exit notice.
     */
    public String showFarewell() {
        return "Have a nice day!\n" + "Exiting...";
    }

    /**
     * Returns specified task from given tasks list as marked done.
     *
     * @param task Task which has been marked as done.
     * @return String showing task as done.
     */
    public String showSetAsDone(Task task) {
        return "Noted. I have marked this task as done:\n"
                + String.format("\t%s%n%n", task);
    }

    /**
     * Returns specified task from given tasks list as added to that list.
     *
     * @param task Task which has been added to list.
     * @return String showing task as added.
     */
    public String showAddTask(Task task) {
        return "Alright! The following task has been added:\n"
                + String.format("\t%s%n", task)
                + String.format("You now have %d %s in the list.%n%n",
                        tasks.getSize(), tasks.getSize() == 1 ? "task" : "tasks");
    }

    /**
     * Returns specified task from given tasks list as being deleted from that list.
     *
     * @param task Task which has been deleted from list.
     * @return String showing task as deleted.
     */
    public String showDeleteTask(Task task) {
        return "Noted. I have removed this task:\n"
                + String.format("\t%s%n", task)
                + String.format("You now have %d %s in the list.%n%n",
                        tasks.getSize(), tasks.getSize() == 1 ? "task" : "tasks");
    }

    /**
     * Returns all tasks from specified tasks list as found task matching some search term.
     *
     * @return String showing tasks matching some search term.
     */
    public String showFound(ArrayList<Task> foundTasks) {
        StringBuilder sb = new StringBuilder();
        
        if (foundTasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("These are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                sb.append(String.format("%d.%s%n", i + 1, foundTasks.get(i)));
            }
        }

        return sb.toString();
    }
    
    public String showTagTask(Task task) {
        return "Added tag to this task:\n"
                + String.format("\t%s%n", task);
    }
}
