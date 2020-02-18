import java.util.ArrayList;

/**
 * Ui class to support output to user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Print goodbye message.
     */
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print out tasks in taskList.
     * @param taskList TaskList to print tasks from
     */
    public static void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (Task s : taskList.arrList) {
            System.out.println((taskList.arrList.indexOf(s) + 1) + "." + s);
        }
    }

    /**
     * Print out tasks in filteredList.
     * @param filteredList ArrayList to print tasks from
     */
    public static void printFilteredList(ArrayList<Task> filteredList) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task s : filteredList) {
            System.out.println((filteredList.indexOf(s) + 1) + ". " + s);
        }
    }

    /**
     * Print exception.
     * @param e Exception to be printed
     */
    public static void printException(Exception e) {
        System.out.println(e);
    }

    /**
     * Print message when task is deleted from arrList.
     * @param task Deleted Task
     * @param arrList ArrayList from which Task is deleted from
     */
    public static void printDelete(Task task, ArrayList<Task> arrList) {
        System.out.println("Noted. I've removed this task: \n"
                + task + "\n\n"
                + "Now you have " + arrList.size() + " tasks in the list.");
    }

    /**
     * Print message when task is done.
     * @param task Done Task
     * @param arrList ArrayList from which Task is marked
     */
    public static void printDone(Task task, ArrayList<Task> arrList) {
        System.out.println("Nice! I've marked this task as done: \n"
                + task);
    }

    /**
     * Print message when task is added.
     * @param task Added Task
     * @param arrList ArrayList to which task is added
     */
    public static void printAdd(Task task, ArrayList<Task> arrList) {
        System.out.println("Got it. I've added this task: \n"
                + task + "\n\n"
                + "Now you have " + arrList.size() + " tasks in the list.");
    }

}
