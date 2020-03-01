public class Gui {

    /**
     * Message displayed at startup of Duke.
     */
    public String printStartUp() {

        return ("Hello, I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * Message displayed at termination of Duke.
     */
    public String terminateGui() {
        return ("Bye. Hope to see you again soon!\n");
    }

    /**
     * Message displayed when file cannot be read.
     */
    public String showLoadingError() {
        return ("File could not be read!\n");
    }

    /**
     * Prints out all tasks in the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     */
    public String printOutTasks(TaskList listOfTasks) {
        String toReturn = "Here are the tasks in your list:\n";
        int i = 1;
        for (Task task : listOfTasks.getTaskList()) {
            toReturn += String.format("% d.%s\n", i, task.toString());
            i++;
        }
        return toReturn;
    }

    public String printOutSnoozed(TaskList listOfTasks, int index, String timing) throws DukeException {
        String taskType = "deadline";
        if(listOfTasks.getTask(index) instanceof Event) {
            taskType = "event";
        }
        String toReturn = "Got it. The updated ";
        toReturn += taskType;
        toReturn += "looks like:\n";
        toReturn += listOfTasks.getTask(index);
        return toReturn;

    }

    /**
     * Prints out updated message when a task is deleted from the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     * @param deleteIndex Index at which the task is to be deleted.
     * @throws DukeException when index is out of bounds of the TaskList.
     */
    public String printOutDeleted(TaskList listOfTasks, int deleteIndex) throws DukeException {
        if (deleteIndex >= listOfTasks.getNumOfTasks() || deleteIndex < 0) {
            throw new DukeException("Index out of bounds!");
        }
        String toReturn = "Noted. I've removed this task:\n";
        toReturn += listOfTasks.getTask(deleteIndex);
        toReturn += String.format("\nNow you have %s tasks in the list\n", listOfTasks.getNumOfTasks() - 1);
        return toReturn;

    }

    /**
     * Prints out updated message when task is marked as done in the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     * @param index       Index at which the task is to be marked as done.
     * @throws DukeException when index is out of bounds of the TaskList.
     */
    public String printOutDoneTask(TaskList listOfTasks, int index) throws DukeException {
        if (index >= listOfTasks.getNumOfTasks() || index < 0) {
            throw new DukeException("Index out of bounds!");
        }
        String toReturn = "Nice, I've marked this task as done:\n  ";
        toReturn += (listOfTasks.getTask(index));

        return toReturn;

    }


    /**
     * Prints out updated message when a task is added to the TaskList.
     *
     * @param listOfTasks List of all saved tasks.
     */
    public String printOutAdded(TaskList listOfTasks) throws DukeException {
        String toReturn = "Got it, I've added this task:\n";
        int num = listOfTasks.getNumOfTasks();
        toReturn += listOfTasks.getTask(num - 1);
        if (num < 2) {
            toReturn += "\nNow you have 1 task in the list.\n";
        } else {
            toReturn += String.format("\nNow you have %d tasks in the list.\n", listOfTasks.getNumOfTasks());
        }
        return toReturn;
    }

    /**
     * Prints out all the tasks which contain the specified keyword.
     *
     * @param listOfTasks ArrayList of tasks matching specified keyword.
     * @return listOfTasks.
     */
    public String printOutFound(TaskList listOfTasks) {
        String toReturn = "Here are the matching tasks in your list:\n";
        int i = 1;
        for (Task task : listOfTasks.getTaskList()) {
            toReturn += String.format("%d.%s\n", i, task);
            i++;
        }
        return toReturn;
    }
}