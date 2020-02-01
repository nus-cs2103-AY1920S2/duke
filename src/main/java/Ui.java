import java.util.Scanner;

/**
 * Ui class that handles user interactions.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a string of welcome text to the user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a string to bid goodbye to the user and exits the program.
     */
    public void showBye() {
        System.out.println("\tBye~ Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Gets the next command from user input.
     *
     * @return A string representation of the next command.
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * Displays acknowledgement whenever a task is added.
     *
     * @param t Task that will be added.
     */
    public void printAdd(Task t) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + TaskList.size() + " " + (TaskList.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    /**
     * Displays acknowledgement whenever a task is deleted.
     *
     * @param toDelete Task that will be deleted.
     */
    public void printDelete(Task toDelete) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + toDelete);
        System.out.println("\tNow you have " + TaskList.size() + " " + (TaskList.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    /**
     * Displays acknowledgement whenever a task is done.
     *
     * @param number An Integer representation of the task index that is to be done.
     * @param tasks A TaskList object that contains ArrayList of Task.
     */
    public void printDone(int number, TaskList tasks) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + tasks.getList().get(number - 1));
    }

    /**
     * Displays acknowledgement whenever user tries to search for a task.
     *
     * @param keyWord A string representation of what the user is trying to search in the list of tasks.
     */
    public void printFind(String keyWord) {
        int index = 1;
        for (int i = 0; i < TaskList.size(); i++) {
            if (TaskList.getList().get(i).getDescription().contains(keyWord)) {
                if (index == 1) {
                    System.out.println("\tHere are the matching tasks in your list:");
                }
                System.out.println("\t" + index + "." + TaskList.getList().get(i));
                index++;
            }
        }
        if (index == 1) {
            System.out.println("\tThere are no matching tasks in your list! Please try something else!");
        }
    }

    /**
     * Displays all the Task objects in the TaskList.
     *
     * @param tasks A TaskList object that contains ArrayList of Task.
     */
    public void listTask(TaskList tasks) {
        System.out.println("\tHere are the " + (tasks.getList().size() == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 1; i <= tasks.getList().size(); i++) {
            System.out.println("\t\t" + i + "." + tasks.getList().get(i - 1));
        }
    }
}
