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
     * Returns DukeBot's welcome message at startup.
     *
     * @return DukeBot's welcome message.
     */
    public String printGuiWelcome() {
        return "Hello!! " + "\u263A" + " I am DukeBot, ready to receive your commands!!\n\n" + "You may type 'manual' "
                + "you need help!";
    }

    /**
     * Prints message that task list is not found.
     */
    public void printLoadingError() {
        System.out.println("Task list not found! Creating one now...");
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
     * @return message showing the task completed by user.
     */
    public String printTaskMarkedDone(Task task) {
        return "Okay noted! You have completed the below task: \n" + task + "\n";
    }

    /**
     * Prints out new Todo task that the user has just inputted and the total number of tasks in the task list.
     *
     * @param task new Todo task inputted by user.
     * @param taskList task list that contains all tasks inputted by user.
     * @return message showing the Todo task user has inputted and the total number of tasks in the task list.
     */
    public String printTodoTask(Task task, ArrayList<Task> taskList) {
        return "Okay! I have taken note of the following:\n" + task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Prints out new Deadline task that the user has just inputted and the total number of tasks in the task list.
     *
     * @param task new Deadline task inputted by user.
     * @param taskList task list that contains all tasks inputted by user.
     * @return message showing the Deadline task that the user has just inputted and the total number of tasks in the
     *     task list.
     */
    public String printDeadlineTask(Task task, ArrayList<Task> taskList) {
        return "Okay! I have taken note of the following:\n" + task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Prints out new Event task that the user has just inputted and the total number of tasks in the task list.
     *
     * @param task new Event task inputted by user.
     * @param taskList task list that contains all tasks inputted by user.
     * @return message showing the Event task that the user has just inputted and the total number of tasks in the
     *     task list.
     */
    public String printEventTask(Task task, ArrayList<Task> taskList) {
        return "Okay! I have taken note of the following:\n" + task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.";
    }

    /**
     * Prints out all tasks in task list that contains the keyword the user has inputted.
     *
     * @param taskList task list that contains all tasks inputted by user.
     * @param keyword keyword to find.
     * @return message showing all tasks in the task list that contains the keyword the user has inputted.
     */
    public String printTasksFound(ArrayList<Task> taskList, String keyword) throws DukeException {
        String strToReturn = "I have found these matching items from your task list:\n";
        String originalString = strToReturn;
        for (Task task : taskList) {
            if (task.getCommand().contains(keyword)) {
                strToReturn = strToReturn + task + "\n";
            }
        }
        if (strToReturn.equals(originalString)) {
            throw new DukeException("ITEM_NOT_FOUND");
        }
        return strToReturn;
    }

    /**
     * Prints out the whole task list thus far.
     *
     * @param taskList task list that contains all tasks inputted by user.
     * @return message showing all tasks saved in the task list.
     */
    public String printList(ArrayList<Task> taskList) {
        String strToReturn = "The below is what you have told me so far. Have you completed them?\n";
        for (Task task : taskList) {
            strToReturn = strToReturn + task + "\n";
        }
        return strToReturn;
    }

    /**
     * Prints out remaining tasks in task list after task mentioned is deleted from the list.
     *
     * @param taskDeleted task to be deleted from task list.
     * @param taskList task list that contains all tasks inputted by user.
     * @return message showing the remaining tasks in task list after task mentioned is deleted from the list.
     */
    public String printRemainingList(Task taskDeleted, ArrayList<Task> taskList) {
        return "Okay noted! I have deleted the below task:\n" + taskDeleted + "\n" + "Now you have "
                + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints out a confirmation message when the user has updated details about a task.
     *
     * @return message that updating of the details of a task has been done.
     */
    public String printUpdatingDone() {
        return "Updating done! Key 'list' again to check:)";
    }
}
