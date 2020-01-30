import java.util.Scanner;

/**
 * Represents the user interface that interacts with the user and acts as a view.
 */
public class Ui {
    /**
     * The input channel.
     */
    Scanner scanner = new Scanner(System.in);

    public void showLoadingError() {
        String message = "Cannot find save file.\n"
                + "Creating a new one, even if it's a bother";

        say(message);
    }

    public void showTasks(TaskList tasks) {
        String message = "Here are your procrastinated tasks:\n"
                + tasks;

        say(message);
    }

    public void showDeletedTask(Task t) {
        String message = "Giving up already? Removed this:\n\t" + t;

        say(message);
    }

    public void showDonetask(Task t) {
        String message = "Yay. You've finally done this task:\n\t" + t;

        say(message);
    }

    public void showAddedTask(Task t, int count) {
        String message = "Another task? Oh well, here's the task:\n"
                + "\t" + t + "\n"
                + "Now you have " + count + " tasks in the list.";

        say(message);
    }

    public void showInvalidFormatMessage() {
        String message = "Your command is not even properly written. Try again properly.";

        say(message);
    }

    public void showWelcome() {
        String message = "Hello. I'm Duke.\n"
                + "Oh, it's you, can you please stop coming?";

        say(message);
    }

    public void showInvalidCommandMessage() {
        String message = "Unknown command. Program it yourself or get a dictionary.";

        say(message);
    }
    
    public void showBye() {
        String message = "Bye. Please don't come back again or I'll call the cops.";

        say(message);
    }

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
