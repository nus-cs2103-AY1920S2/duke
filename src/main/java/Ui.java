import java.util.ArrayList;

/**
 * Contains methods which handle interactions with the user
 */
public class Ui {
    private int maxLength;

    public Ui() {
        this.maxLength = 60;
    }

    /**
     * Display welcome message
     */
    public void showWelcome() {
        String logo = " _________                        \n" +
                " __  ____/_______________________ \n" +
                " _  / __ _  __ \\  __ \\_  ___/  _ \\  >(o )___\n" +
                " / /_/ / / /_/ / /_/ /(__  )/  __/   ( ._> /\n" +
                " \\____/  \\____/\\____//____/ \\___/     `---'";

        System.out.println("Hello it's\n" + logo);

        String greeting = "Honk, this Goose. Do you need help with something?";
        System.out.println("\n" + wrapLine(greeting));
    }

    /**
     * Display goodbye message
     */
    public void showBye() {
        System.out.println(wrapLine("Honk honk!"));
    }

    /**
     * Display task list
     *
     * @param taskList Task list as a String (ideally loaded from Storage)
     */
    public void showList(String taskList) {
        System.out.println(wrapLine("Honk! Here's your task list: \n" + taskList));
    }

    /**
     * Message to be displayed after task has been added
     *
     * @param addedTask Task that was added
     * @param taskList Updated task list
     */
    public void showAddTask(Task addedTask, ArrayList<Task> taskList) {
        String addHeader = " Honk! Okay added the task:\n  ";
        System.out.println(wrapLine(addHeader + addedTask + showCount(taskList)));
    }

    /**
     * Message to be displayed after task has been marked as done.
     *
     * @param doneTask Task that was marked as done
     */
    public void showDoneTask(Task doneTask) {
        String doneHeader = "Good job! I've honked it as done:\n  ";
        System.out.println(wrapLine(doneHeader + doneTask));
    }

    /**
     * Message to be displayed after task has been deleted.
     *
     * @param deletedTask Task that was deleted
     * @param taskList Updated task list
     */
    public void showDeleteTask(Task deletedTask, ArrayList<Task> taskList) {
        String deleteHeader = "Honk! Removed this task from the list:\n  ";
        System.out.println(wrapLine(deleteHeader + deletedTask + showCount(taskList)));
    }

    /**
     * Displays list of found tasks when a user uses the 'find' command
     *
     * @param foundTasks ArrayList of tasks which match the search
     */
    public void showFoundTasks(ArrayList<Task> foundTasks) {
        String foundHeader = foundTasks.isEmpty()
                ? "Goose didn't find anything, honk..."
                : "Honk! Here are the matching tasks in your list:\n";

        // print list of found tasks
        String foundList = "";
        for (int i = 0; i < foundTasks.size(); i++) {
            int indexNum = i + 1;
            String item = " " + indexNum + "." + foundTasks.get(i).toString();
            if (i != foundTasks.size() - 1) {
                item += "\n";
            }
            foundList += item;
        }

        System.out.println(wrapLine(foundHeader + foundList));
    }

    /**
     * Displays count of current tasks in task list.
     *
     * @param taskList Current task list as an ArrayList
     * @return String containing formatted message regarding task count of task list.
     */
    private String showCount(ArrayList<Task> taskList) {
        String count = taskList.size() == 1
                ? "\n\n Now you have " + taskList.size() + " task in the list."
                : "\n\n Now you have " + taskList.size() + " tasks in the list.";
        return count;
    }

    // Exceptions

    /**
     * Displays loading error message
     */
    public void showLoadingError() {
        System.out.println(wrapLine("Honk! Loading error!"));
    }

    /**
     * Displays formatted error message
     *
     * @param msg Error message to be displayed
     */
    public void showError(String msg) {
        System.out.println(wrapLine(msg));
    }

    // Formatting

    /**
     * Wraps message in appropriate format to be displayed.
     *
     * @param msg Message to be wrapped
     * @return String containing formatted messsage
     */
    public String wrapLine(String msg) {
        String top = "";
        for (int i = 0; i < maxLength; i++) {
            top += "_";
        }
        top += "\n";

        String bottom = "__  ";
        for (int i = 0; i < maxLength - 4; i++) {
            bottom += "_";
        }
        String bottomArrow = "\n   /\n";

        return top + "\n" + " " + msg + "\n" + bottom + bottomArrow;
    }
}