/**
 * This class handles all displays
 */

public class Ui {

    /**
     * Creates a new instance of the class Ui
     */

    public Ui() {

    }

    /**
     * Shows loading error when the input file is empty
     */

    public void showLoadingError() {
        System.out.println("The input file is empty. A new list will be created");
    }

    /**
     * Shows input error when the user added an invalid input
     */

    public void showInputError() {
        System.out.println("The input is invalid. Please try again");
    }

    /**
     * Shows the greeting message when the user starts Duke
     */

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows the confirmation message after the task is successfully added
     * @param task This is the task that was successfully added to the task list
     * @param size This is the new size of the task list after the new task is added
     */

    public static void gotIt(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + size + " tasks in the list");
    }
}

