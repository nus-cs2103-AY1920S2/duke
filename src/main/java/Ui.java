import java.util.Scanner;

/**
 * Text Ui of Duke.
 */
public class Ui {
    private Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Gets user input.
     *
     * @return String of next user input.
     */
    public String getInput() {
        return reader.nextLine();
    }

    /**
     * Shows line break.
     */
    public void printBreak() {
        System.out.println("    ______________________________________________________________");
    }

    /**
     * Shows Duke logo.
     */
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreak();
    }

    /**
     * Shows greeting message.
     */
    public void printGreet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBreak();
    }

    /**
     * Shows all tasks in the list.
     *
     * @param tasks Task list.
     */
    public void list(TaskList tasks) {
        printBreak();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getTaskNumber(); i++) {
            Task currTask = tasks.getTask(i - 1);
            System.out.println("    " + i + "." + currTask);
        }
        printBreak();
    }

    /**
     * Shows adding response.
     *
     * @param t New Task to be added.
     * @param tasks Task list.
     */
    public void add(Task t, TaskList tasks) {
        printBreak();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + t);
        tasks.addTask(t);
        System.out.println("    Now you have " + tasks.getTaskNumber() + " tasks in the list.");
        printBreak();
    }

    /**
     * Shows marking as done message.
     *
     * @param currTask Task that be marked.
     */
    public void markDone(Task currTask) {
        printBreak();
        System.out.println("    Nice! I've marked this task as done:");
        currTask.markAsDone();
        System.out.println("      " + currTask);
        printBreak();
    }

    /**
     * Shows deleting message.
     *
     * @param currTask Target Task to delete.
     * @param tasks Task list.
     */
    public void delete(Task currTask, TaskList tasks) {
        printBreak();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + currTask);
        tasks.removeTask(currTask);
        System.out.println("    sNow you have " + tasks.getTaskNumber() + " tasks in the list.");
        printBreak();
    }

    /**
     * Shows exiting message.
     */
    public void printExit() {
        printBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        printBreak();
    }

    /**
     * Shows Exception message.
     *
     * @param message Exception message.
     */
    public void printException(String message) {
        printBreak();
        System.out.println(message);
        printBreak();
    }

    /**
     * Shows all Tasks that matches the key word.
     *
     * @param keyWord String of key word.
     * @param tasks Task list.
     */
    public void find(String keyWord, TaskList tasks) {
        printBreak();
        System.out.println("    Here are the matching tasks in your list:");
        boolean isfailed = true;
        for(int i = 1; i <= tasks.getTaskNumber(); i++) {
            Task currTask = tasks.getTask(i - 1);
            if (currTask.getDescription().contains(keyWord)) {
                System.out.println("    " + i + "." + currTask);
                isfailed = false;
            }
        }
        if (isfailed) {
            System.out.println("    None.");
        }
        printBreak();
    }

}
