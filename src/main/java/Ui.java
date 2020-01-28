import java.util.Scanner;

public class Ui {
    private Scanner sc;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Message displayed at startup of Duke.
     */
    public void printStartUp() {
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Message displayed at termination of Duke.
     */
    public void terminateUi() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Message displayed when file cannot be read.
     */
    public void showLoadingError() {
        System.out.println("File could not be read!");
    }

    /**
     * Prints out all tasks in the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     */
    public void printOutTasks(TaskList listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task task : listOfTasks.getTaskList()) {
            System.out.printf("%d.%s\n", i, task);
            i++;
        }
    }

    /**
     * Prints out updated message when a task is deleted from the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     * @param deleteIndex Index at which the task is to be deleted.
     * @throws DukeException when index is out of bounds of the TaskList.
     */
    public void printOutDeleted(TaskList listOfTasks, int deleteIndex) throws DukeException {
        if (deleteIndex >= listOfTasks.getNumOfTasks() || deleteIndex < 0) {
            throw new DukeException("Index out of bounds!");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + listOfTasks.getTask(deleteIndex));
        System.out.printf("Now you have %s tasks in the list \n", listOfTasks.getNumOfTasks() - 1);

    }

    /**
     * Prints out updated message when task is marked as done in the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     * @param index Index at which the task is to be marked as done.
     * @throws DukeException when index is out of bounds of the TaskList.
     */
    public void printOutDoneTask(TaskList listOfTasks, int index) throws DukeException {
        if (index >= listOfTasks.getNumOfTasks() || index < 0) {
            throw new DukeException("Index out of bounds!");
        }
        System.out.println("Nice, I've marked this task as done:");
        System.out.println(" " + listOfTasks.getTask(index));

    }

    /**
     * Returns the input provided by user.
     *
     * @return input provided.
     */
    public String newInput() {
        Scanner sc = new Scanner(System.in);
        return (sc.nextLine());
    }

    /**
     * Prints out updated message when a task is added to the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     */
    public void printOutAdded(TaskList listOfTasks) {
        System.out.println("Got it, I've added this task:");
        if (listOfTasks.getNumOfTasks() < 2) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", listOfTasks.getNumOfTasks());
        }
    }

    public void printOutFound(TaskList listOfTasks) {
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for(Task task : listOfTasks.getTaskList()) {
            System.out.printf("%d.%s\n", i, task);
            i++;
        }
    }
}
