package duke.handler;

import duke.entity.*;
import duke.entity.task.Task;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Say welcome to the user upon initializing programme
     */
    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Say bye to user upon terminating programme
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a line to separate different sections
     */
    public void showLine() {
        System.out.println("____________________________");
    }

    /**
     * Scans the user input for their next command
     *
     * @return String input entered by user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a message confirming the addition of a task
     *
     * @param task Task that was added
     * @param taskList current TaskList being used in the programme
     */
    public void confirmAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints a message confirming the deletion of a task
     *
     * @param task Task that was deleted
     * @param taskList current TaskList being used in the programme
     */
    public void confirmDeleteTask(Task task, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.printTask());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints a message confirming the completion of a task
     *
     * @param index index of task that was completed
     * @param task task that was completed
     */
    public void confirmDoneTask(int index, String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(index + 1 + "." + task);
    }

    /**
     * Prints a message prompting that an invalid format has been entered
     */
    public void invalidFormatError() {
        System.out.println("Invalid format for input!");
    }

    /**
     * Prints a message prompting that an error has been encountered when saving to file
     */
    public void errorSavingChanges() {
        System.out.println("Error saving changes!");
    }

    /**
     * Prints all tasks currently saved
     *
     * @param tasks TaskList currently being used by programme
     */
    public void listAllTasks(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).printTask());
        }
    }

    /**
     * Prints all tasks due by a certain date
     *
     * @param tasks list of tasks that are due
     * @param date due date specified by user
     */
    public void listDueTasks(List<Task> tasks, Date date) {
        System.out.println("Here is a list of events and deadlines: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).printTask());
        }
    }

    /**
     * Prints a customer message determined by programme that doesn't fall under any of the other categories
     *
     * @param message message to be printed
     */
    public void printCustomMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts user that details are missing from their command
     */
    public void missingDetails() {
        System.out.println("☹ OOPS!!! Information about the task is missing");
    }

    /**
     * Prints all tasks that matches a user-input keyword
     *
     * @param tasks list of tasks that are found
     */
    public void listFoundTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).printTask());
        }
    }
}
