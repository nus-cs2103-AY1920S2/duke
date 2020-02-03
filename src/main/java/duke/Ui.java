package duke;

/**
 * The Ui class than handles display of the Duke program.
 */
public class Ui {
    /**
     * Prints a greeting message.
     */
    public static void greet() {
        System.out.println("Greeting, traveler. My name is Andrew. What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public static void goodbye() {
        System.out.println("I shall not trouble you anymore. Farewell, partner.");
    }

    /**
     * Prints a message when an addition command is completed.
     *
     * @param str  a String description of the task.
     * @param size the number of Task objects in the current list after the addition.
     */
    public static void printAdd(String str, int size) {
        System.out.printf("Added:\t %s\n", str);
        System.out.printf("You now have %d tasks in your list\n", size);
    }

    /**
     * Prints a message when a deletion command is completed
     *
     * @param str  a String description of the task.
     * @param size the number of Task objects in the current list after the deletion.
     */
    public static void printDel(String str, int size) {
        System.out.printf("Your burden has been lifted, removed: \n\t %s\n", str);
        System.out.printf("You now have %d tasks in your list\n", size);
    }

    /**
     * Prints a message when a done command is completed
     *
     * @param str  a String description of the task
     * @param size the number of Task objects in the current list after the completion.
     */
    public static void printDone(String str, int size) {
        System.out.printf("Task successfully completed: \n\t %s\n", str);
        System.out.printf("You now have %d tasks in your list\n", size);
    }

    /**
     * Prints a message error.
     *
     * @param e an Exception.
     */
    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints all the available commands that the Duke program can understand.
     */
    public static void printTaskList() {
        System.out.println("Commands available:");
        System.out.println("1. event [description] : add a new event");
        System.out.println("2. deadline [description] by yyyy-mm-dd hhmm : add a new deadline");
        System.out.println("3. todo [description] at yyyy-mm-dd hhmm : add a new todo");
        System.out.println("4. done [index] : complete the task at given index");
        System.out.println("5. delete [index] : delete the task at given index");
        System.out.println("6. bye : say goodbye and quit");
    }


}
