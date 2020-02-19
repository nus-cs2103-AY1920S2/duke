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
                + "if you need help!";
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

    /**
     *Prints out information about all possible commands that the user can input.
     *
     * @return message showing all possible commands that the user can input, and example inputs.
     */
    public String printManual() {
        String helpMsg = "Don't be shy to ask for help!! \nThese are the commands that I can understand, please "
                + "follow them so I can reply to you accordingly!^-^";
        String todoTask = "1. todo "  + "\n" + "- To add a Todo task to the task list, with the corresponding "
                + "description of the task\n" + "e.g. 'todo exercise'";
        String deadlineTask = "2. deadline " + "\n" + "- To add a Deadline task to the task list, with the description "
                + "and the corresponding date and time at which the task is due\n" + "e.g. 'deadline Assignment 1 /by "
                + "2020-02-01 1200'";
        String eventTask = "3. event " + "\n" + "- To add a Event task to the task list, with the description and the "
                + "timing details of the event\n" + "'e.g. event meeting /at "
                + "Sunday 2pm'";
        String listCommand = "4. list " + "\n" + "- To list out all lists available in the task list\n" + "e.g. 'list'";
        String doneCommand = "5. done " + "\n" + "- To mark a task as completed, with its index specified" + "\n"
                + "e.g. 'done 1' (this will mark the first task in the list as done)";
        String findCommand = "6. find " + "\n" + "- To find all tasks present in the task list that fit a keyword "
                + "inputted" + "\n" + "e.g 'find book'";
        String deleteCommand = "7. delete " + "\n" + "- To delete a task from the task list, with its index specified"
                + "\n" + "e.g. 'delete 1' (this will delete the first task in the task list)";
        String updateCommand = "8. update " + "\n" + "- To update the description of a Todo task, the deadline date "
                + "and time of a Deadline task or the timing details of a Event task, using the specific command "
                + "entered previously" + "\n" + "e.g. 'update borrow book /borrow children's book for YRO' "
                + "(given that 'todo borrow book' was inputted previously)";
        return helpMsg + "\n\n" + todoTask + "\n\n" + deadlineTask + "\n\n" + eventTask + "\n\n" + listCommand
                + "\n\n" + doneCommand + "\n\n" + findCommand + "\n\n" + deleteCommand + "\n\n" + updateCommand;
    }
}
