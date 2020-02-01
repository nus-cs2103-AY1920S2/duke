package duke.main;

import duke.exceptions.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Class to handle all input and output interactions with the User
 * Can be expanded to handle internationalisation
 */
public class Ui {
    /**
     * Scanner object to get User input
     */
    static Scanner scanner = new Scanner(System.in);

    /**
     * Display message of error that occurred during loading
     */
    public static void showLoadingError() {
        System.out.println("Error loading Storage module. Your tasks may not be loaded or saved.");
    }

    /**
     * Prints out initial prompt
     */
    public static void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

    }

    /**
     * Prints out ending prompt
     */
    public static void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Get User's input as a String
     * @return User's input
     */
    public static String getInput() {
        return scanner.nextLine();
    }

    /**
     * Prints out a generic object
     * @param object Object to be printed
     */
    public static void print(Object object) {
        System.out.println(object);
    }

    /**
     * Prints out a String
     * @param line String to be printed
     */
    public static void print(String line) {
        System.out.println(line);
    }

    /**
     * Prints out a list of strings
     * @param lines Strings to be printed
     */
    public static void print(List<String> lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Prints out a message indicating the successful addition of a Task to the TaskList
     * @param task Task that was added
     * @param taskList TaskList that the task was added to
     */
    public static void taskAdded(Task task, TaskList taskList) {
        print("Got it. I've added this task:\n" + task);
        print("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    /**
     * Prints out a message indicating the successfully marking of a Task as done
     * @param task Task that was marked as done
     */
    public static void taskMarkedAsDone(Task task) {
        print("Nice! I've marked this task as done:");
        print(task);
    }

    /**
     * Prints out a message indicating the successful deletion of a Task to the TaskList
     * @param task Task that was deleted
     * @param taskList TaskList that the task was deleted from
     */
    public static void taskDeleted(Task task, TaskList taskList) {
        print("Noted. I've removed this task:");
        print(task);
        print("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    /**
     * Prints out a message indicating the successful clearing of all Tasks in the TaskList
     */
    public static void tasksCleared() {
        print("All tasks have been cleared");
    }

    /**
     * Prints out a message indicating that there are no Tasks for a given date
     */
    public static void calendarNoItems() {
        print("No matching events/deadlines found.");
    }

    /**
     * Prints out the list of Deadlines and Events occurring on a given date
     * @param calendarDate Date in question
     * @param lines String representation of the Deadlines and Events to be printed
     */
    public static void calendarDisplayItems(LocalDate calendarDate, List<String> lines) {
        print("Here are the events/deadlines in your list on " +
            calendarDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ":");
        print(lines);
    }

    /**
     * Prints out a message indicating that there are no Tasks in the TaskList.
     */
    public static void printNoTasks() {
        print("You have no tasks in your list.");
    }

    /**
     * Prints out the list of Tasks in TaskList
     * @param lines String representation of the Tasks to be printed
     */
    public static void printTasks(List<String> lines) {
        print("Here are the tasks in your list:");
        print(lines);
    }

}
