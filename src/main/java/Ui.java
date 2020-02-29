/**
 * This class handles all string messages.
 */

public class Ui {

    /**
     * Creates a new instance of the class Ui.
     */

    public Ui() {

    }

    /**
     * Shows input error when the user added an invalid input.
     */

    public static String showInputError() {
        return "The input is invalid. Please try again"
                + System.lineSeparator();
    }

    /**
     * Shows the empty description error message.
     * @param s The task type where the error occurs.
     */

    public static String emptyError(String s) {
        return s + " description cannot be empty"
                + System.lineSeparator();
    }

    /**
     * Greets the user when Duke starts.
     */

    public static String greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String text = "";
        text += "Hello from\n" + logo;
        text += "Hello! I'm Duke\n";
        text += "What can I do for you?";
        return text;
    }

    /**
     * Shows the re-enter error message.
     * @param s The task type where the error occurs.
     */

    public static String reEnterDate(String s) {
        return "Please re-enter the date for " + s
                + System.lineSeparator();
    }

    /**
     * Shows the wrong date error message.
     */

    public static String wrongDate() {
        return "Please enter a valid date format in the form of YYYY-MM-DD";
    }

    /**
     * Shows the invalid number error message.
     * @param s The keyword where the number input is invalid
     */

    public static String invalidNumber(String s) {
        return "Please enter a valid task number to " + s
                + System.lineSeparator();
    }

    /**
     * Shows the confirmation message after the task is successfully added.
     * @param task This is the task that was successfully added to the task list.
     * @param size This is the new size of the task list after the new task is added.
     */

    public static String gotIt(Task task, int size) {
        String text ="";
        text += "Got it. I've added this task: \n" + task
                + System.lineSeparator();
        text += "Now you have " + size + " tasks in the list"
                + System.lineSeparator();
        return text;
    }
}