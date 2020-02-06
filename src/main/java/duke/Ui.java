package duke;
public class Ui {
    private static String content;

    public static void greet() {
        content = "Greeting, traveler. My name is Andrew. What can I do for you?";
        System.out.println(content);
    }

    public static String getContent() {
        return content;
    }

    /**
     * Prints a goodbye message.
     */
    public static void goodbye() {
        content = "I shall not trouble you anymore. Farewell, partner.";
        System.out.println(content);
    }

    /**
     * Prints a message when an addition command is completed.
     *
     * @param str  a String description of the task.
     * @param size the number of Task objects in the current list after the addition.
     */
    public static void printAdd(String str, int size) {
        content = String.format("Added:\t %s\nYou now have %d tasks in your list\n", str, size);
        System.out.println(content);
    }

    /**
     * Prints a message when a deletion command is completed
     *
     * @param str  a String description of the task.
     * @param size the number of Task objects in the current list after the deletion.
     */
    public static void printDel(String str, int size) {
        content = String.format("Your burden has been lifted, removed: \n\t %s\nYou now have %d tasks in your list\n"
                , str, size);
        System.out.println(content);
    }

    /**
     * Prints a message when a done command is completed
     *
     * @param str  a String description of the task
     * @param size the number of Task objects in the current list after the completion.
     */
    public static void printDone(String str, int size) {
        content = String.format("Task successfully completed: \n\t %s\nYou now have %d tasks in your list\n"
                , str, size);
        System.out.println(content);
    }

    /*
     * Prints a message when a find command is initiated.
     * @param str the keyword provided
     */
    public static void printFindPre(String str) {
        content = String.format("Tasks that contain %s in your list\n", str);

        System.out.println(content);
    }

    /**
     * Prints a message showing the number of matches found.
     * @param size the number of matches
     */
    public static void printFindPost(int size) {
        content = String.format("%d %s in total.\n", size, size > 1 ? "entries" : "entry");

        System.out.println(content);
    }

    /**
     * Prints a message error.
     *
     * @param e an Exception.
     */
    public static void printError(Exception e) {
        content = e.getMessage();
        System.out.println(content);
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
        System.out.println("6. find [one single keyword] : find records that contain the specified keyword");
        System.out.println("7. bye : say goodbye and quit");
    }


}
