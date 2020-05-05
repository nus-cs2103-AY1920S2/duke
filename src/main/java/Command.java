/**
 * Represents an executable command
 */
public abstract class Command {
    TaskList taskList;

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute();

    /**
     * Supplies the data that command will operate on
     * @param taskList the single taskList used in Duke
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a String telling the user that the add command was successful
     * @param newTask
     * @return a String feedback for the user
     */
    public String getAddSuccessMessage(Task newTask) {
        assert(taskList != null);
        return "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

}
