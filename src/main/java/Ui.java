public class Ui {
    protected static String fill = "----------------------------------------";
    protected static String indent = "    ";

    public void showAddTask(Task task, int size) {
        System.out.println(dukeFormat("Sure I will add this task.\n    " + task +
                "\n    Now you have " + String.valueOf(size) + " tasks."));
    }

    public void showDoneTask(Task task) {
        System.out.println(dukeFormat("Sure I will mark this task as done.\n    " + task));
    }

    public void showDeleteTask(Task task, int size) {
        System.out.println(dukeFormat("Sure I will delete this task.\n    " + task
                + "\n    Now you have " + String.valueOf(size) + " tasks."));
    }

    public void showGreeting() {
        System.out.println(dukeFormat("Hello I'm your mum. What can I do for you?"));
    }

    public void showBye() {
        System.out.println(dukeFormat("Bye, have a good day!"));
    }

    public void showErrInvalidInput() {
        System.err.println(dukeFormat("Please try again, your input is invalid."));
    }

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
