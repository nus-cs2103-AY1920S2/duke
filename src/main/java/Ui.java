import java.util.ArrayList;

/**
 * A Ui object deals with interaction with the user, by printing the appropriate response to the user input.
 */
public class Ui {
    /**
     * Prints welcome message when Duke is started.
     */
    public void printWelcome() {
        System.out.println("Hi! I am Duke! What would you like to tell me today?:)");
    }

    /**
     * Prints message that task list is not found.
     */
    public void printLoadingError() {
        System.out.println("Task list not found! Creating one now...");
    }

    /**
     * Prints out respective exception.
     *
     * @param exception DukeException resulted from user's command input.
     */
    public void printError(Exception exception) {
        System.out.println(exception.toString());
    }

    /**
     * Prints goodbye message when user says bye.
     */
    public void printExitLine() {
        System.out.println("Okay then! Goodbye!");
    }

    /**
     * Prints out task completed by user.
     *
     * @param task Task marked as 'Done' by user.
     */
    public void printTaskMarkedDone(Task task) {
        System.out.println("Okay noted! You have completed the below task:\n" + task);
    }

    /**
     * Prints out new Todo task that the user has just inputted and the total number of tasks in the task list.
     *
     * @param task new Todo task inputted by user.
     * @param taskList task list that contains all tasks inputted by user.
     */
    public void printTodoTask(Task task, ArrayList<Task> taskList) {
        System.out.println("Okay! I have taken note of the following:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints out new Deadline task that the user has just inputted and the total number of tasks in the task list.
     *
     * @param task new Deadline task inputted by user.
     * @param taskList task list that contains all tasks inputted by user.
     */
    public void printDeadlineTask(Task task, ArrayList<Task> taskList) {
        System.out.println("Okay! I have taken note of the following:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints out new Event task that the user has just inputted and the total number of tasks in the task list.
     *
     * @param task new Event task inputted by user.
     * @param taskList task list that contains all tasks inputted by user.
     */
    public void printEventTask(Task task, ArrayList<Task> taskList) {
        System.out.println("Okay! I have taken note of the following:\n" + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints out all tasks in task list that contains the keyword the user has inputted.
     *
     * @param taskList task list that contains all tasks inputted by user.
     * @param keyword keyword to find.
     */
    public void printTasksFound(ArrayList<Task> taskList, String keyword) {
        System.out.println("I have found these matching items from your task list:");
        for (Task task : taskList) {
            if (task.getCommand().contains(keyword)) {
                System.out.println(task);
            }
        }
    }

    /**
     * Prints out the whole task list thus far.
     *
     * @param taskList task list that contains all tasks inputted by user.
     */
    public void printList(ArrayList<Task> taskList) {
        System.out.println("The below is what you have told me so far. Have you completed them?");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    /**
     * Prints out remaining tasks in task list after task mentioned is deleted from the list.
     *
     * @param taskDeleted task to be deleted from task list.
     * @param taskList task list that contains all tasks inputted by user.
     */
    public void printRemainingList(Task taskDeleted, ArrayList<Task> taskList) {
        System.out.println("Okay noted! I have deleted the below task:\n" + taskDeleted);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}
