package commons;

/**
 * Stores magic strings as constants to be returned as output.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unfurtunately, I don't know what that means :(";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASK_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String LOGO = " ___           _\n"
            + "|  _  \\ _    _| |  _  __\n"
            + "| |  |  | |  | | |/ /   _ \\\n"
            + "| |_|  | |_| |   <    __/\n"
            + "|____/ \\__,|_|\\_\\___|\n";
    public static final String START = "Hello from\n" + LOGO;
    public static final String HELP = "alias: creates shortcut\n\t[alias] [command]\n"
            + "bye: exits the programme\n"
            + "clear: clears list\n"
            + "deadline: creates task with a deadline \n\t[description] [date] [tags]\n"
            + "delete: deletes task with task number\n\t[task number\n"
            + "done: marks task with task number as done\n\t[task number]\n"
            + "event: creates task that is an event \n\t[description] [date] [tags]\n"
            + "find: returns list of tasks with keyword in name\n\t[keyword]\n"
            + "tag: returns list of tasks containing tag\n\t [tag]\n"
            + "todo: creates task \n\t[description] [tags]\n";

    /**
     * Returns information on the number of tasks in the list.
     *
     * @return the string of the total number of tasks in the list.
     */
    public static String printTotalTasks(int totalTasks) {
        if (totalTasks == 0) {
            return "\nNow you have no tasks in the list.";
        } else if (totalTasks == 1) {
            return String.format("\nNow you have %d task in the list.", totalTasks);
        } else {
            return String.format("\nNow you have %d tasks in the list.", totalTasks);
        }
    }


}
