import java.util.Scanner;

/**
 * Represents the user interface that interacts with the user and acts as a view.
 */
public class Ui {
    /**
     * The input channel.
     */
    Scanner scanner = new Scanner(System.in);

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
     * @param tasks the list of tasks
     */
    public void showTasks(TaskList tasks) {
        String message = "Here are your procrastinated tasks:\n"
                + tasks;

        say(message);
    }

    /**
     * Displays the deleted task.
     * @param task the deleted task
     */
    public void showDeletedTask(Task task) {
        String message = "Giving up already? Removed this:\n\t" + task;

        say(message);
    }

    /**
     * Displays the task marked as completed.
     * @param task the completed task
     */
    public void showDonetask(Task task) {
        String message = "Yay. You've finally done this task:\n\t" + task;

        say(message);
    }

    /**
     * Displays the newly added task and the total number of tasks.
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
        String message = "Bye. Please don't come back again or I'll call the cops.";

        say(message);
    }

    /**
     * Displays the list of tasks containing the given keyword.
     * @param tasks the task containing the given keyword
     */
    public void showFoundTasks(TaskList tasks) {
        String message = "Here are your oh so hard to find tasks:\n"
                + tasks;

        say(message);
    }

    /**
     * Retrieves the user command from the interface.
     * @return the user command
     */
    public String getCommand() {
        return scanner.nextLine();
    }

    private void say(String speech) {
        String separator = "\t============================================================\n";

        String[] lines = speech.split("\n");
        String indented = "\t " + String.join("\n\t ", lines) + "\n";

        String result = separator + indented + separator;

        System.out.println(result);
    }
}
