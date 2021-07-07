import java.util.Scanner;

/**
 * Represents the user interface that interacts with the user and acts as a view.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private String message = "";

    /**
     * Displays a message indicating an error in loading the task list save file.
     */
    public void showLoadingError() {
        String message = "Cannot find save file.\n"
                + "Creating a new one, even if it's a bother";

        say(message);
    }

    /**
     * Displays the list of tasks given.
     *
     * @param tasks the list of tasks
     */
    public void showTasks(TaskList tasks) {
        String message = "Here are your procrastinated tasks:\n"
                + tasks;

        say(message);
    }

    /**
     * Displays the deleted task.
     *
     * @param task the deleted task
     */
    public void showDeletedTask(Task task) {
        String message = "Giving up already? Removed this:\n\t" + task;

        say(message);
    }

    /**
     * Displays the task marked as completed.
     *
     * @param task the completed task
     */
    public void showDoneTask(Task task) {
        String message = "Yay. You've finally done this task:\n\t" + task;

        say(message);
    }

    /**
     * Displays the newly added task and the total number of tasks.
     *
     * @param task the added task
     * @param count the total number of tasks
     */
    public void showAddedTask(Task task, int count) {
        String message = "Another task? Oh well, here's the task:\n"
                + "\t" + task + "\n"
                + "Now you have " + count + " tasks in the list.";

        say(message);
    }

    /**
     * Displays an error indicating improperly formatted command.
     */
    public void showInvalidFormatMessage() {
        String message = "Your command is not even properly written. Try again properly.";

        say(message);
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        String message = "Hello. I'm Duke.\n"
                + "Oh, it's you, can you please stop coming?";

        say(message);
    }

    /**
     * Displays an error indicating an unknown command.
     */
    public void showInvalidCommandMessage() {
        String message = "Unknown command. Program it yourself or get a dictionary.";

        say(message);
    }

    /**
     * Displays a closing message.
     */
    public void showBye() {
        String message = "Bye. Please don't come back again or I'll call the cops.\n"
                + "Closing in a few seconds...";

        say(message);
    }

    /**
     * Displays the list of tasks containing the given keyword.
     *
     * @param tasks the task containing the given keyword
     */
    public void showFoundTasks(TaskList tasks) {
        String message = "Here are your oh so hard to find tasks:\n"
                + tasks;

        say(message);
    }

    /**
     * Displays the snoozed task.
     *
     * @param task the snoozed task
     */
    public void showSnoozedTask(Task task) {
        String message = "I knew you would procrastinate even more. Here's delaying the inevitable:\n"
                + task;

        say(message);
    }

    /**
     * Displays the snooze error message.
     *
     * @param task the task that cannot be snoozed
     */
    public void showInvalidSnooze(Task task) {
        String message = "Yeah, can't snooze this task:\n"
                + task;

        say(message);
    }

    /**
     * Displays the index out of bounds message.
     */
    public void showInvalidIndexMessage() {
        String message = "Invalid index. Go revise your maths.";

        say(message);
    }


    /**
     * Retrieves the user command from the interface.
     *
     * @return the user command
     */
    public String getCommand() {
        return scanner.nextLine();
    }


    /**
     * Returns the last printed message.
     *
     * @return the last printed message
     */
    protected String getResponse() {
        return message;
    }

    /**
     * Prints the speech with decorations.
     *
     * @param speech the message to be said
     */
    protected void say(String speech) {
        message = speech;
        String separator = "\t============================================================\n";

        String[] lines = speech.split("\n");
        String indented = "\t " + String.join("\n\t ", lines) + "\n";

        String result = separator + indented + separator;

        System.out.println(result);
    }
}
