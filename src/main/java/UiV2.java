/** Handles user output. */
public class UiV2 {

    /**
     * Prints output for adding a task into the TaskList.
     *
     * @param task task to be added.
     * @param size current size of TaskList.
     */
    public String sendAddTask(Task task, int size) {
        if (task != null) {
            return ("Sure I will add this task.\n" + task
                    + "\n" + "Now you have " + String.valueOf(size) + " tasks.");
        } else {
            return "";
        }
    }

    /**
     * Prints output for setting a task to be done in the TaskList.
     *
     * @param task task to be set to done.
     */
    public String sendDoneTask(Task task) {
        if (task != null) {
            return ("Sure I will mark this task as done.\n" + task);
        } else {
            return "";
        }
    }

    /**
     * Prints output for deleting a task from the TaskList.
     *
     * @param task task to be deleted.
     * @param size current size of TaskList.
     */
    public String sendDeleteTask(Task task, int size) {
        if (task != null) {
            return ("Sure I will delete this task.\n" + task
                    + "\n" + "Now you have " + String.valueOf(size) + " tasks.");
        } else {
            return "";
        }
    }

    /**
     * Prints initial greeting.
     */
    public String sendGreeting() {
        return ("Hello I'm your mum. What can I do for you? Send 'help' for instructions.");
    }

    /**
     * Prints instructions.
     */
    public String sendInstructions() {
        return ("Here are the commands you can use!"
                + "\ntodo - adds a simple task "
                + "\ndeadline - adds a task specifying a deadline"
                + "\nevent - adds a task specifying a starting and ending date/time"
                + "\nnote - adds a note tag and a corresponding note"
                + "\nfind - I will compile all tasks with the specified keyword/s"
                + "\nlist - I will display all your tasks"
                + "\ndone - marks a chosen task as done"
                + "\ndelete - deletes a chosen task");
    }

    /**
     * Prints error for invalid input.
     */
    public String sendErrInvalidInput() {
        return ("Please try again, your input is invalid.");
    }

    /**
     * Prints TaskList.
     */
    public String sendList(TaskList lst) {
        String res = "";
        if (lst.getSize() > 0) {
            for (int i = 0; i < lst.getSize(); i++) {
                res += String.valueOf(i + 1) + ". " + lst.getTask(i) + "\n";
            }
        } else {
            res = ("Your list is empty!");
        }
        return res;
    }
}
