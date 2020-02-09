package duke;

/**
 * The type Ui.
 */
public class Ui {

    /**
     * Shows start message.
     */
    public void showStartMessage() {
        System.out.println("Hello Sir\nWhat can I do for you today?");
    }

    /**
     * Shows goodbye message.
     */
    public void showGoodbyeMessage() {
        System.out.println("Have a nice day sir!");
    }

    /**
     * Shows task done message.
     *
     * @param task       the task
     * @param taskNumber the task number
     */
    public void showTaskDoneMessage(Task task, int taskNumber) {
        System.out.println("I shall mark task " + taskNumber + " as completed");
        System.out.println(task.toString());
    }

    /**
     * Shows task list.
     *
     * @param taskList the task list
     */
    public void showTaskList(MyList taskList) {
        System.out.println("Here are the list of task:");
        taskList.printList();
        System.out.println("You have " + taskList.getArraySize() + " task");
    }

    /**
     * Shows task added message.
     *
     * @param task     the task
     * @param taskList the task list
     */
    public void showTaskAddedMessage(Task task, MyList taskList) {
        System.out.println("I have added the following task:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskList.getArraySize() + " task");
    }

    /**
     * Shows task deleted message.
     *
     * @param taskList   the task list
     * @param taskNumber the task number
     */
    public void showTaskDeletedMessage(MyList taskList, int taskNumber) {
        Task taskToDelete = taskList.getTask(taskNumber);
        String taskName = taskToDelete.getItem();
        System.out.println("I shall delete task: " + taskName);
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
}
