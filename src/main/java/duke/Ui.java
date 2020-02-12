package duke;

/**
 * The type Ui.
 */
public class Ui {

    /**
     * Shows start message.
     *
     * @return the string
     */
    public String showStartMessage() {
        return "Hello Sir\nWhat can I do for you today?";
    }

    /**
     * Shows goodbye message.
     *
     * @return the string
     */
    public String showGoodbyeMessage() {
        return "Have a nice day sir!";
    }

    /**
     * Shows task done message.
     *
     * @param task       the task
     * @param taskNumber the task number
     * @return the string
     */
    public String showTaskDoneMessage(Task task, int taskNumber) {
        return "I shall mark task " + taskNumber + " as completed" + "\n" + task.toString();
    }

    /**
     * Shows task list.
     *
     * @param taskList the task list
     * @return the string
     */
    public String showTaskList(MyList taskList) {
        String result = "";
        result += "Here are the list of task:" + "\n";
        result += taskList.printList();
        result += "You have " + taskList.getArraySize() + " task";
        return result;
    }

    /**
     * Shows task added message.
     *
     * @param task     the task
     * @param taskList the task list
     * @return the string
     */
    public String showTaskAddedMessage(Task task, MyList taskList) {
        String result = "";
        result += "I have added the following task: \n";
        result += task.toString() + "\n";
        result += "You now have " + taskList.getArraySize() + " task";
        return result;
    }

    /**
     * Shows task deleted message.
     *
     * @param taskList   the task list
     * @param taskNumber the task number
     * @return the string
     */
    public String showTaskDeletedMessage(MyList taskList, int taskNumber) throws DukeException {
        Task taskToDelete = taskList.getTask(taskNumber);
        String taskName = taskToDelete.getItem();
        return "I shall delete task: " + taskName;
    }

    /**
     * Shows wrong command error.
     *
     * @return the string
     */
    public String showWrongCommandError() {
        return "You have entered a command I do not understand";
    }

    /**
     * Shows deadline description error.
     *
     * @return the string
     */
    public String showDeadlineDescriptionError() {
        return "Sorry, the description of a deadline cannot be empty";
    }

    /**
     * Shows event description error.
     *
     * @return the string
     */
    public String showEventDescriptionError() {
        return "Sorry, the description of an event cannot be empty";
    }

    /**
     * Shows todo description error.
     *
     * @return the string
     */
    public String showTodoDescriptionError() {
        return "Sorry, the description of todo cannot be empty";
    }

    /**
     * Shows task does not exist message.
     *
     * @return the string
     */
    public String showTaskDoesNotExistMessage() {
        return "Unable to delete a task that does not exist";
    }

    /**
     * Show found results message.
     *
     * @return the string
     */
    public String showFoundResultsMessage() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Shows message if no task matches the find.
     *
     * @return the string
     */
    public String showNoTaskFoundMessage() {
        return "No task found that matches your description";
    }

    public String showOutOfBoundError() {
        return "Task does not exist, you have input a wrong number";
    }

    public String showWrongDateException() {
        return "Please key in a valid date in the format yyyy-MM-dd";
    }

}
