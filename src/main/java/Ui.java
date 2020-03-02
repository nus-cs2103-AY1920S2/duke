import java.util.ArrayList;

public class Ui {

    /**
     * Prints start up message to user
     */
    public static void sayHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    Hello! I'm Duke\n" + "  What can I do for you?");
    }

    /**
     * Prints termination message to user
     */
    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out formatted tasks in list form
     * @param taskArrList an ArrayList representing the stored tasks
     */
//    public static void showTasks(TaskList taskArrList) {
//        for(Task t : taskArrList) {
//
//        }
//    }

    public static void noExistingSaveFile() {
        System.out.println("WUBBA LUBBA DUB DUB");
    }
}
