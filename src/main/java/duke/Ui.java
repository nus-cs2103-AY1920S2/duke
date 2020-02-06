package duke;

import duke.exceptions.EmptyTaskListException;
import duke.tasks.Task;

/**
 * User Interface class which is centred about a strong user experience and its associated methods.
 */
public class Ui {

    /**
     * Prints removal of a given task, of its index then and the current number of Tasks remaining.
     *
     * @param index index of current Task to remove.
     */
    public static void printTaskRemoval(int index, Task removedTask, TaskList allTasks) {
        printLine();
        print("Removed Task #" + (index + 1) + ": " + removedTask
                + "\nHope it's worth it!\nYou are now left with "
                + allTasks.sizeOf() + " tasks.");
        printLine();
    }

    /**
     * Prints all tasks, their number order, and their completion for list command.
     */
    public static void printAllTasks(TaskList allTasks) throws EmptyTaskListException {
        printLine();
        if (allTasks.isEmpty()) {
            throw new EmptyTaskListException("");
        }
        for (int i = 0; i < allTasks.sizeOf(); i++) {
            printTaskFromStored(i, allTasks);
        }
        printLine();
    }

    /**
     * Prints a response to the done command after doTask completes.
     *
     * @param t Task that has been completed via doTask method.
     */
    public static void printTaskComplete(Task t) {
        printLine();
        if (t.getIsDone()) {
            print("That's already done, try another. Or did you make a careless mistake? XD");
        } else {
            print("Nice! The following task has been marked completed:\n"
                    + "===> [V] " + t + " <===");
        }
        printLine();
    }

    /**
     * Prints individual task with current completion status.
     *
     * @param i index of storage of the Task in the container/collection.
     */
    public static void printTaskFromStored(int i, TaskList allTasks) {
        String tickOrCross = allTasks.getTask(i).obtainStatusIcon();
        print(String.valueOf(i + 1) + ". [" + tickOrCross + "] " + allTasks.getTask(i));
    }

    /**
     * Welcomes the user with a message.
     */
    public static void welcome() {
        printLine();
        print("Hello, duke.Duke here! :D \n"
                + "I'm feeling good and ready to go! "
                + "What can I do for you?");
        printLine();
    }

    /**
     * Says goodbye to the user.
     */
    public static void goodbye() {
        printLine();
        print("====> Alright byeee thanks for coming and see ya soon! <==== \n:D :D :D :D :D");
        printLine();
    }

    /**
     * Prints a given String.
     *
     * @param e String to be printed.
     */
    public static void printExceptionMessage(String e) {
        printLine();
        print(e);
        printLine();
    }

    /**
     * Prints a horizontal formatting line.
     */
    public static void printLine() {
        print(Constant.FORMAT_LINE);
    }

    /**
     * Prints a given String.
     *
     * @param s String to be printed.
     */
    public static void print(String s) {
        System.out.println(s);
    }

}
