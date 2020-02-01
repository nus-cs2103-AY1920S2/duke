/** Handles user output. */
public class Ui {
    protected static String fill = "----------------------------------------";
    protected static String indent = "    ";

    /**
     * Prints output for adding a task into the TaskList.
     *
     * @param task task to be added.
     * @param size current size of TaskList.
     */
    public void showAddTask(Task task, int size) {
        System.out.println(dukeFormat("Sure I will add this task.\n" + Ui.indent + task +
                "\n" + Ui.indent + "Now you have " + String.valueOf(size) + " tasks."));
    }

    /**
     * Prints output for setting a task to be done in the TaskList.
     *
     * @param task task to be set to done.
     */
    public void showDoneTask(Task task) {
        System.out.println(dukeFormat("Sure I will mark this task as done.\n" + Ui.indent + task));
    }

    /**
     * Prints output for deleting a task from the TaskList.
     *
     * @param task task to be deleted.
     * @param size current size of TaskList.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println(dukeFormat("Sure I will delete this task.\n" + Ui.indent + task
                + "\n" + Ui.indent + "Now you have " + String.valueOf(size) + " tasks."));
    }

    /**
     * Prints initial greeting.
     */
    public void showGreeting() {
        System.out.println(dukeFormat("Hello I'm your mum. What can I do for you?"));
    }

    /**
     * Prints goodbye message.
     */
    public void showBye() {
        System.out.println(dukeFormat("Bye, have a good day!"));
    }

    /**
     * Prints error for invalid input.
     */
    public void showErrInvalidInput() {
        System.err.println(dukeFormat("Please try again, your input is invalid."));
    }

    /**
     * Prints TaskList.
     */
    public void showList(TaskList lst) {
        System.out.println(dukeFormatList(lst));
    }

    /**
     * Formats a string to an output produced by Duke.
     * @param s string to be formatted.
     * @return formatted string ready to be printed.
     */
    private String dukeFormat(String s) {
        return indent + fill + "\n" + indent + s + "\n" + indent + fill;
    }

    /**
     * Formats a list to an output produced by Duke.
     * @param lst list to be formatted.
     * @return Formatted list ready to be printed.
     */
    private String dukeFormatList(TaskList lst) {
        String res = "";
        res += indent + fill + "\n";
        for (int i = 0; i < lst.getSize(); i++) {
            res += indent + String.valueOf(i + 1) + ". " + lst.getTask(i) + "\n";
        }
        res += indent + fill;
        return res;
    }

}
