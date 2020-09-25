package duke.io;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TasksList;
import java.util.Scanner;

/**
 * This class handles what is displayed and inputted into the program.
 * It provides methods to easily to get input and parse it
 * as well as print out commonly used text to output.
 */
public class Ui {
    // INPUT ///////////////////////////////////////////////////////////////////////////////////
    /**
     * Get the next line of input from the user after he hits return
     *
     * @return returns the input as a String
     *  without white space at the start or end of it.
     */
    public String getInput() {
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        input = input.trim();

        return input;
    }

    /**
     * Processes the input for further manipulation.
     *
     * @param input the unprocessed input.
     * @return  Returns an array where the very first word (command) is put into index 0 and everything else (arguments) in index 1
     */
    public String[] separateCommandAndArguments(String input) {
        return input.split(" ", 2);
    }

    // OUTPUT //////////////////////////////////////////////////////////////////////////////////
     /**
      * All duke exception are handled by this method, which prints out the error message
      *  contained in the exception.
      *
      * @param exception the unhandled exception.
      */
    public void handleException(DukeException exception) {
        print("Exception!" + System.lineSeparator() +
                "\t" + exception.description);
    }

    /**
     * Short hand for printing.
     *
     * @param text text to print
     */
    public void print(String text) {
        System.out.println(text);
    }

    /**
     * prints a line to separate messages, etc
     */
    public void printLineSeparator() {
        print("---------------------------------------");
    }

    /**
     * prints the number of tasks in the tasksList
     *
     * @param tasksList the tasksList which we want the size of
     */
    public void printNumOfTasks(TasksList tasksList) {
        int numOfTasks = tasksList.size();
        print("Now you have " + numOfTasks + " task(s) in the list.");
    }

    /**
     * Prints message to greet user or other info he might want to know
     */
    public void printHomeScreen() {
        printLineSeparator();

        print("Hello! I'm Duke" + System.lineSeparator() +
                "What can I do for you?");

        printLineSeparator();
    }

    /**
     * Prints message to persuade the user to come back and use Duke
     */
    public void printExitScreen() {
        print("Bye. Hope to see you again soon!");

        printLineSeparator();
    }

    /**
     * Prints that a task has been added to tasksList successfully and then prints
     * the number of tasks tasksList has now
     *
     * @param tasksList The TaskList who was just added to. Thus increased in size.
     * @param newTask The Task just added
     */
    public void printSuccessfulAddEntry(TasksList tasksList, Task newTask) {
        printLineSeparator();

        print("Got it. I've added this task:" + System.lineSeparator() +
                "\t" + newTask);
        printNumOfTasks(tasksList);

        printLineSeparator();
    }

    /**
     * Prints that a task has been deleted to tasksList successfully and then prints
     * the number of tasks tasksList has now
     *
     * @param tasksList The TaskList who was just added to. Thus increased in size.
     * @param deletedTask The Task just deleted
     */
    public void printSuccessfulRemoveEntry(TasksList tasksList, Task deletedTask) {
        printLineSeparator();

        print("Got it. I've deleted this task:" + System.lineSeparator() +
                "\t" + deletedTask);
        printNumOfTasks(tasksList);

        printLineSeparator();
    }

    /**
     * Prints that data has been loaded into tasksList
     */
    public void printDataLoadSuccess() {
        printLineSeparator();
        print("Successfully loaded Data!");
        printLineSeparator();
    }

    /**
     * Prints the each task and its details in the taskList
     * as well as a numbering in order from first element(top) to last (bottom)
     * @param tasksList  the taskList to print tasks from
     */
    public void printList(TasksList tasksList) {
        int entryNum = 1;

        printLineSeparator();

        for(Task task : tasksList.tasks) {
            if(task == null) {
                break;
            }

            print(entryNum + "." + task);
            entryNum++;
        }

        printLineSeparator();
    }
}
