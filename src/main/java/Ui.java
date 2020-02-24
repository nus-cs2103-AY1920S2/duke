/**
 * Class that deals with interactions with the user.
 */

public class Ui {
    public Ui() {
    }

    /**
     * Returns a string containing the welcome message.
     * @return The welcome message for user.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        return logo + greeting;
    }

    /**
     * Returns a string containing the goodbye message.
     * @return The goodbye message for user.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a string containing the list of tasks in the provided list.
     * @param taskList List of tasks to be printed as a string.
     * @return A string containing the list of tasks.
     */
    public String showList(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            int itemNo = i + 1;
            Task task = taskList.showList().get(i);
            if (i == taskList.getTotalTasks() - 1) {
                output += itemNo + "." + task;
            } else {
                output += itemNo + "." + task + "\n";
            }
        }

        return output;
    }

    /**
     * Marks a task as completed and returns a string containing the specific task.
     * @param taskList List of tasks containing the task to be marked completed.
     * @param index Index of the task to be marked completed.
     * @param storage Storage object to save the action.
     * @return A string containing the completed task.
     */
    public String showDone(TaskList taskList, int index, Storage storage) {
        assert index <= taskList.getTotalTasks(): "Index given exceeds total tasks";
        taskList.markDone(index);
        String output = "Nice! I've marked this task as done\n"
                + "  " + taskList.showList().get(index);
        storage.saveData(taskList.showList());

        return output;
    }

    /**
     * Deletes a specified task from the list of tasks and and returns a string containing the task.
     * @param taskList List of tasks containing the task to be deleted.
     * @param index Index of the task to be deleted.
     * @param storage Storage object to save the action.
     * @return A string containing the deleted task.
     */
    public String showDelete(TaskList taskList, int index, Storage storage) {
        assert index <= taskList.getTotalTasks(): "Index given exceeds total tasks";
        String output = "Noted. I've removed this task:\n"
                + "  " + taskList.showList().get(index);
        taskList.delete(index);
        storage.saveData(taskList.showList());

        return output;
    }

    /**
     * Adds a tasks to the list of tasks and and returns a string containing the task.
     * @param taskList List of tasks to add the task to.
     * @param content Content of the task.
     * @param storage Storage object to save the action.
     * @return A string containing the added task.
     */
    public String showAddedTask(TaskList taskList, Parser content, Storage storage) {
        taskList.add(content.getTask());
        String output = "Got it. I've added this task:\n"
                + "  " + content.getTask() + "\n" + "Now you have "
                + taskList.getTotalTasks() + " task(s) in the list.";
        storage.saveData(taskList.showList());

        return output;
    }

    /**
     * Searches the list of tasks for tasks containing the keyword and returns a string containing the tasks.
     * @param taskList List of tasks to search through.
     * @param keyword Keyword to search for.
     * @return A string containing the list of tasks with the specified keyword.
     */
    public String showSearchResults(TaskList taskList, String keyword) {
        TaskList results = taskList.find(keyword);

        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < results.getTotalTasks(); i++) {
            int itemNo = i + 1;
            Task task = results.showList().get(i);
            if (i == results.getTotalTasks() - 1) {
                output += itemNo + "." + task;
            } else {
                output += itemNo + "." + task + "\n";
            }
        }

        return output;
    }

    /**
     * Updates a task from the list of task and returns a string containing the specific task.
     * @param taskList List of tasks containing the task to be updated.
     * @param index Index of the task to be updated.
     * @param type The portion of the task to be updated.
     * @param content The new content of the updated portion.
     * @param storage Storage object to save the action.
     * @return A string containing the updated task.
     * @throws DukeException If the specified portion of task is not valid/can not be updated.
     */
    public String showUpdate(TaskList taskList, int index, String type,
                             String content, Storage storage) throws DukeException{
        taskList.update(index, type, content);
        String output = "Alright. I've updated this task:\n"
                + "  " + taskList.showList().get(index);
        storage.saveData(taskList.showList());

        return output;
    }

    /**
     * Returns a string containing the message of the given exception.
     * @param exception The exception thrown.
     * @return A string containing the message of the given exception.
     */
    public String showError(Exception exception) {
        return exception.getMessage();
    }
}
