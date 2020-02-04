package jiachen.duke;

/**
 * The Ui class handles the view and presentation layer of the app
 */
public class Ui {
    public String formatHeader() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";

        StringBuilder builder = new StringBuilder(logo);
        builder.append("\t______________________________________________________");
        builder.append("\tHello! I'm Duke");
        builder.append("\tWhat can I do for you?");


        builder.append("\t______________________________________________________");
        return builder.toString();
    }

    public String format(String message) {
        return formatSeparator() + "\t" + message + "\n" + formatSeparator();
    }


    public String formatError(String errorMessage) {
        return errorMessage;
    }

    public String formatRemoveTask(Task task) {
        return formatSeparator() + "\t Noted. I've removed this task:\t\t" + task + "\n" + formatSeparator();
    }

    public String formatDoneTask(Task task) {
        return formatSeparator() + "\t Nice! I've marked this task as done: \n\t\t" + task + formatSeparator();
    }

    public String formatTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder(formatSeparator());
        for (int i = 1; i <= tasks.getList().size(); i++) {
            builder.append("\t ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        builder.append(formatSeparator());
        return builder.toString();
    }


    public String formatNewTask(Task task, int numOfTasks) {
        return formatSeparator() + "\t Got it. I've added this task: \n"
                + "\t\t"
                + task
                + "\n"
                + "\t Now you have "
                + numOfTasks
                + " tasks in the list.\n" + formatSeparator();
    }

    public String formatLoadingError() {
        return "\tERR: unable to load file from disk!\n";
    }

    private String formatSeparator() {
        return "\t_____________________________________________________";
    }

    public String formatFilteredTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder(formatSeparator());
        builder.append("\n\tHere are the matching tasks in your list:\n");

        for (int i = 1; i <= tasks.getList().size(); i++) {
            builder.append("\t ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        builder.append(formatSeparator());
        return builder.toString();
    }

}
