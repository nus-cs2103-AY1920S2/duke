import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui Class deals with interaction with the user.
 * Includes logos and all messages that is sent to the user.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * print message with logo on startup.
     */
    public void welcomeMessage() {
        System.out.println("Hello from Duke!");
        System.out.println("What can I do for you today?");
        System.out.println("Please tell me what to do!");
        System.out.println("\n");
        System.out.println("====================================================================================");
    }

    /**
     * print when a "bye" Command is run.
     */
    public void terminateMessage() {
        System.out.println("\n");
        System.out.println("====================================================================================");
        System.out.println("Bye bye! Thank you for using me! Hope to see you again soon.");
    }

    public void showLineBreak() {
        System.out.println("====================================================================================");
    }

    public String handleInput() {
        return scanner.nextLine();
    }

    /**
     * Error message of no existing file at the current <code>filePath</code>.
     */
    public void showLoadingError() {
        System.out.println("There is no saved To Do List on your Hard disk!");
    }

    public static void printAfterDone(Task currentTask) {
        System.out.println("Nice! I've marked this task as done and dusted:");
        System.out.println(currentTask);
    }

    public static void printAfterDelete(Task currentTask, ArrayList<Task> listOfTexts) {
        System.out.println("Bye bye Task! I've removed this task:");
        System.out.println(currentTask);
        System.out.println("Now you have " + listOfTexts.size() + " tasks in the list.");
    }

    public static void printAfterTodo(Todo todo, ArrayList<Task> listOfTexts) {
        assert (listOfTexts.size() != 0) : "☹ OOPS!!! No tasks to show: add some todo/deadline/event/fixedDurationTask now! ☹ OOPS!!!";
        System.out.println("Got you covered! Added this task to the list: ");
        System.out.println(todo);
        System.out.println("Now you have " + listOfTexts.size() + " tasks in the list.");
    }

    public static void printAfterDeadline(Deadline d, ArrayList<Task> listOfTexts) {
        assert (listOfTexts.size() != 0) : "☹ OOPS!!! No tasks to show: add some todo/deadline/event/fixedDurationTask now! ☹ OOPS!!!";
        System.out.println("Got you covered! Added this deadline to the list:");
        System.out.println(d);
        System.out.println("Now you have " + listOfTexts.size() + " tasks in the list.");
    }

    public static void printAfterEvent(Event e, ArrayList<Task> listOfTexts) {
        assert (listOfTexts.size() != 0) : "☹ OOPS!!! No tasks to show: add some todo/deadline/event/fixedDurationTask now! ☹ OOPS!!!";
        System.out.println("Got you covered! Added this event to the list: ");
        System.out.println(e);
        System.out.println("Now you have " + listOfTexts.size() + " tasks in the list.");
    }
    public static void printAfterFixedTask(FixedDurationTask fdt, ArrayList<Task> listOfTexts) {
        assert (listOfTexts.size() != 0) : "☹ OOPS!!! No tasks to show: add some todo/deadline/event/fixedDurationTask now! ☹ OOPS!!!";
        System.out.println("Got you covered! Added this FixedDurationTask to the list: ");
        System.out.println(fdt);
        System.out.println("Now you have " + listOfTexts.size() + " tasks in the list.");
    }

    public static void printBeforeSearch() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void printSearch(ArrayList<Task> searchList) {
        assert (searchList.size() != 0) : "☹ OOPS!!! No match on your search! Are you sure you've added it? ☹ OOPS!!!";
        int counter = 1;
        for (int i = 0; i < searchList.size(); i++) {
            System.out.println(counter + ". " + searchList.get(i));
            counter++;
        }
    }
    public static void printEmptyToDoList() {
        System.out.println("To Do List is empty! Congratulations!");
    }

}
