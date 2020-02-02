package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui handles tasks related to the user interface of Duke. It deals with user interaction, including getting
 * user input and generating replies from Duke.
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
     * Returns a loading error due to Duke being unable to load in saved tasks from the file specified. Informs
     * the user that a new file will be created.
     * 
     * @return Loading error string.
     */
    public String showLoadingError() {
        return "Error: Unable to load saved tasks from file.\n"
                + "New data file will be created.";
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
     * @param tasks List of tasks.
     * @return List of tasks.
     */
    public String showTasks(TaskList tasks) {
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
        
//        ArrayList<Task> list = tasks.getList();
//        
//        System.out.println("\tHere are your tasks:");
//
//        if (list.isEmpty()) {
//            System.out.println("\t<No tasks have been added>");
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.format("\t%d.%s%n", i + 1, list.get(i));
//        }
//
//        System.out.println();
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
     * @param taskList List of tasks.
     * @param task Task which has been marked as done.
     * @return String showing task as done.
     */
    public String showSetAsDone(TaskList taskList, Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I have marked this task as done:\n");
        sb.append(String.format("\t%s%n%n", task));

        return sb.toString();
        
//        System.out.println("\tNoted. I have marked this task as done:");
//        System.out.format("\t\t%s%n%n", task);
    }

    /**
     * Returns specified task from given tasks list as added to that list.
     * 
     * @param taskList List of tasks.
     * @param task Task which has been added to list.
     * @return String showing task as added.
     */
    public String showAddTask(TaskList taskList, Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! The following task has been added:\n");
        sb.append(String.format("\t%s%n", task));
        sb.append(String.format("You now have %d %s in the list.%n%n", 
                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks"));
        
        return sb.toString();
        
//        System.out.println("\tAlright! The following task has been added:");
//        System.out.format("\t\t%s%n", task);
//        System.out.format("\tYou now have %d %s in the list.%n%n",
//                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks");
    }

    /**
     * Returns specified task from given tasks list as being deleted from that list.
     * 
     * @param taskList List of tasks.
     * @param task Task which has been deleted from list.
     * @return String showing task as deleted.
     */
    public String showDeleteTask(TaskList taskList, Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I have removed this task:\n");
        sb.append(String.format("\t%s%n", task));
        sb.append(String.format("You now have %d %s in the list.%n%n",
                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks"));

        return sb.toString();
        
//        System.out.println("\tNoted. I have removed this task:");
//        System.out.format("\t\t%s%n", task);
//        System.out.format("\tYou now have %d %s in the list.%n%n",
//                taskList.getSize(), taskList.getSize() == 1 ? "task" : "tasks");
    }

    /**
     * Returns all tasks from specified tasks list as found task matching some search term.
     * 
     * @param tasks List of tasks containing tasks matching some search term.
     * @return String showing tasks matching some search term.
     */
    public String showFound(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        
        if (tasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("These are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(String.format("%d.%s%n", i + 1, tasks.get(i)));
            }
        }

        return sb.toString();
    }
}
