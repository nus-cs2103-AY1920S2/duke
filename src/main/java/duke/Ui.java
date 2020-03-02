package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui handle tasks related to the user interface of Duke. It also handles user interaction, such as getting
 * user input and generating replies from Duke.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs an Ui object. A scanner will be created upon object creation to read in user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static final String DELIMITER = "___________________________________________________";

    /**
     * Prints the starting message when Duke starts.
     */
    public void showGreeting() {
        System.out.println(DELIMITER);
        System.out.println("> Hello! I'm Duchess");
        System.out.println("> What can I do for you?");
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints the exit message when user ends a Duke session.
     */
    public void showExitMessage() {
        System.out.println("> Bye! Hope you don't come back!");
    }

    /**
     * Returns the input from user.
     * Uses Scanner to read in from System.in.
     *
     * @return User input.
     */
    public String getInput() {
        return this.sc.nextLine();
    }

    /**
     * Prints out message showing the task the user added into the task list.
     *
     * @param tasks List of tasks.
     * @param task The task the user added into the list of tasks.
     */
    public void showAddedTask(TaskList tasks, Task task) {
        System.out.println(DELIMITER);
        System.out.println("> Alrighty, you added:");
        System.out.println("  " + task.toString());
        System.out.printf("> Now you have %d tasks in your list.\n", tasks.getSize());
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints out message showing the task the user deleted from the task list.
     *
     * @param tasks List of tasks.
     * @param task The task the user deleted from the list of tasks.
     */
    public void showDeletedTask(TaskList tasks, Task task) {
        System.out.println(DELIMITER);
        System.out.println("> Do you really want to remove this?");
        System.out.println("> Fine. I've removed:\n>    " + task.description);
        System.out.printf("> Now you have %d tasks in your list\n", tasks.getSize());
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints out message showing the task the user marked as done.
     *
     * @param task The task the user marked as done.
     */
    public void showDoneTask(Task task) {
        System.out.println(DELIMITER);
        System.out.println("> I've finally done this task:");
        System.out.println(task);
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints out message showing task(s) that the user searched for.
     * @param tasks The task(s) that the user searched for
     */
    public void showFoundTasks(TaskList tasks) {
        System.out.println(DELIMITER);
        System.out.println("Here are the matching tasks in your list: ");
        System.out.println(tasks);
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints out an error message from any kind of DukeException caught.
     *
     * @param e DukeException caught.
     */
    public void showError(DukeException e) {
        e.printStackTrace();
        System.out.print("> ");
    }

    /**
     * Prints out an error message arising from reading in tasks from saved file.
     *
     * @param e Exception caught from trying to read the save file.
     */
    public void showLoadingError(Exception e) {
        System.out.println("Can't load saved data");
        e.printStackTrace();
    }

    /**
     * Prints out all the tasks from the task list.
     *
     * @param tasks List of tasks.
     */
    public static void showTaskList(TaskList tasks) {
        System.out.println(DELIMITER);
        System.out.println("Here are the tasks that you will never complete: ");
        System.out.println(tasks);
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints out all the tasks from the task list that got archived.
     * @param tasks List of tasks.
     */
    public void showArchiveMessage(TaskList tasks) {
        System.out.println(DELIMITER);
        System.out.println("Here are the tasks that you have archived: ");
        System.out.println(tasks);
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    /**
     * Prints the starting message when Duke starts.
     */
    public String getGreeting() {
        return
        "Hello! I'm Peach\n" +
        "What can I do for you?";
    }

    /**
     * Prints the exit message when user ends a Duke session.
     */
    public String getExitMessage() {
        return "Bye! Hope you don't come back!";
    }

    /**
     * Prints out message showing the task the user added into the task list.
     *
     * @param tasks List of tasks.
     * @param task The task the user added into the list of tasks.
     */
    public String getAddedTask(TaskList tasks, Task task) {
        return "Alrighty, you added:\n" +
        "  " + task.toString() + "\n" +
        String.format("Now you have %d tasks in your list.\n", tasks.getSize());
    }

    /**
     * Prints out message showing the task the user deleted from the task list.
     *
     * @param tasks List of tasks.
     * @param task The task the user deleted from the list of tasks.
     */
    public String getDeletedTask(TaskList tasks, Task task) {
        return "Do you really want to remove this?\n" +
        "Fine. I've removed:\n>    " + task.description + "\n" +
        String.format("> Now you have %d tasks in your list\n", tasks.getSize());
    }

    /**
     * Prints out message showing the task the user marked as done.
     *
     * @param task The task the user marked as done.
     */
    public String getDoneTask(Task task) {
        return "You've finally done this task: \n" +
        task;
    }

    /**
     * Prints out message showing task(s) that the user searched for.
     * @param tasks The task(s) that the user searched for
     */
    public String getFoundTasks(TaskList tasks) {
        return "Here are the matching tasks in your list: \n" +
        tasks;
    }

    /**
     * Prints out an error message from any kind of DukeException caught.
     *
     * @param e DukeException caught.
     */
    public String getError(DukeException e) {
        return e.toString();
    }

    /**
     * Prints out an error message arising from reading in tasks from saved file.
     *
     * @param e Exception caught from trying to read the save file.
     */
    public String getLoadingError(Exception e) {
        return "Can't load saved data\n" +
        e.toString();
    }

    /**
     * Prints out all the tasks from the task list.
     *
     * @param tasks List of tasks.
     */
    public static String getTaskList(TaskList tasks) {
        return "Here are the tasks that you will never complete: \n" +
        tasks;
    }

    /**
     * Prints out all the tasks from the task list that got archived.
     * @param tasks List of tasks.
     */
    public String getArchiveMessage(TaskList tasks) {
        return "Here are the tasks that you have archived: \n" + tasks;
    }
}
