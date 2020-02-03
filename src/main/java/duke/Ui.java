package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static final String DELIMITER = "___________________________________________________";

    public void showGreeting() {
        System.out.println(DELIMITER);
        System.out.println("> Hello! I'm Duchess");
        System.out.println("> What can I do for you?");
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    public void showExitMessage() {
        System.out.println("> Bye! Hope you don't come back!");
    }

    public String getInput() {
        return this.sc.nextLine();
    }

    public void showAddedTask(TaskList tasks, Task task) {
        System.out.println(DELIMITER);
        System.out.println("> Alrighty, you added:");
        System.out.println("  " + task.toString());
        System.out.printf("> Now you have %d tasks in your list.\n", tasks.getSize());
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    public void showDeletedTask(TaskList tasks, Task task) {
        System.out.println(DELIMITER);
        System.out.println("> Do you really want to remove this?");
        System.out.println("> Fine. I've removed:\n>    " + task.description);
        System.out.printf("> Now you have %d tasks in your list\n", tasks.getSize());
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    public void showDoneTask(Task task) {
        System.out.println(DELIMITER);
        System.out.println("> I've finally done this task:");
        System.out.println(task);
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

    public void showError(Exception e) {
        e.printStackTrace();
        System.out.print("> ");
    }

    public void showLoadingError(Exception e) {
        System.out.println("Can't load saved data");
        e.printStackTrace();
    }

    public static void showTaskList(TaskList tasks) {
        System.out.println(DELIMITER);
        System.out.println("Here are the tasks that you will never complete: ");
        System.out.println(tasks);
        System.out.println(DELIMITER);
        System.out.print("> ");
    }

}
