import java.util.ArrayList;

/**
 * UI class supports everything that is displayed to the user.
 */
public class UI {

    /**
     * Empty constructor.
     */
    public UI() {
    }

    /**
     * String of asterisks for design purposes
     */
    static String horizontalLine = "***********************************************";

    /**
     * Prints the introduction logo when program is first started.
     */
    static void printIntro() {
        System.out.println(horizontalLine);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello, I'm Duke \n"
                + "How can I help you today?");
        System.out.println(horizontalLine);
    }

    /**
     * Prints the goodbye message when user inputs "bye".
     */
    public static void printBye() {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    /**
     * Prints out the tasks in the list.
     */
    public static void printList() {
        ArrayList<Task> list = TaskList.getTaskList();
        System.out.println(horizontalLine);
        if (list.isEmpty()) {
            System.out.println("You have 0 outstanding tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Task s : list) {
                int listNo = list.indexOf(s) + 1;
                System.out.println(listNo + "." + s);
            }
        }
        System.out.println(horizontalLine);
    }

    /**
     * Prints out the inputted string wrapped within the line of asterisks.
     * @param string String to be wrapped within.
     */
    public static void wrapper(String string) {
        String output = horizontalLine + "\n" + string + "\n" + horizontalLine;
        System.out.println(output);
    }
}
