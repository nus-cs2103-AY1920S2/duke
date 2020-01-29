package ui;
import java.util.Scanner;

import task.Task;
import task.TaskList;
/**
 * Represents the user interface displayed to the user.
 */
public class Ui {
    public Ui(){}
    /**
     * Displays the welcome message at the start of the program.
     */
    public void showWelcome(){
        String line = "    ____________________________________________________________" + "\n";
        String fiveSpaces = "      ";
        System.out.println(line + fiveSpaces + "Hello! I'm Duke\n" + fiveSpaces + "Whatcha wanna do?\n" + line);
    }
    /**
     * Reads the command entered by the user.
     * @return The text representation of the command entered by the user.
     */
    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    /**
     * Displays a long line.
     */
    public void showLine(){
        System.out.println("    ____________________________________________________________" );
    }
    /**
     * Displays the error when the storage file could not be found.
     */
    public void showLoadingError(){
        System.out.println("File not found. Created a new data directory and created a duke.txt inside. Data will be stored here.");
    }
    /**
     * Displays the error when the storage file could not be updated.
     */
    public void showSavingError() {System.out.println("File not saved");}
    /**
     * Displays the error.
     * @param error The error occurred.
     */
    public void showError(String error){
        System.out.println(error);
    }
    /**
     * Displays the message to the user that a task has been added.
     * @param task The task that is added.
     * @param taskList The current task list.
     */
    public void taskAdded(Task task, TaskList taskList){
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + Integer.toString(taskList.tasks.size()) + " tasks in the list.");
    }

    /**
     * Displays the message to the user that a task has been deleted.
     * @param task The task that is deleted.
     * @param taskList The current task list.
     */
    public void taskDeleted(Task task, TaskList taskList){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + Integer.toString(taskList.tasks.size()) + " tasks in the list.");
    }
    /**
     * Displays the message to the user that a task has been marked as done.
     * @param task The task that has been marked as done.
     */
    public void taskDone(Task task){
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
    }
    /**
     * Displays to the user the current task list.
     * @param taskList The current task list.
     */
    public void printList(TaskList taskList){
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.tasks.size(); i++) {
            System.out.println("      " + Integer.toString(i + 1) + "." + taskList.getTask(i));
        }
    }
    /**
     * Displays the program termination message.
     */
    public void showByeMsg(){
        System.out.print("     See ya later alligator!\n");
    }
}
