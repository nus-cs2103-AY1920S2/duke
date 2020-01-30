import java.util.Scanner;

/**
 * The UI of the program. It is responsible for interacting with the user.
 */
public class Ui {

    /**
     * The scanner object to get user input.
     */
    protected Scanner sc;

    /**
     * Constructs a new instance of the UI.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Gets the user input command from the console using Scanner class.
     *
     * @return the user input command.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a divider line.
     */
    public void printDivider() {
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        System.out.println("> Hi! I'm Aelita, guardian of Lyoko.");
        System.out.println("  How can I help you?");
        printDivider();
    }

    /**
     * Prints the logo.
     */
    public void printLogo() {
        final String logo = "     __             _   _     _\n"
                + "    /  \\           | | / \\   | |\n"
                + "   / /\\ \\     ___  | | \\_/ __| |__   ___ _\n"
                + "  / /__\\ \\   / _ \\ | |  _ |__   __| / _ | |\n"
                + " / ______ \\ |  __/ | | | |   | |   | |_|  |\n"
                + "/_/      \\_\\ \\___\\ |_| |_|   |_|    \\___/\\_\\";
        System.out.println(logo);
        System.out.println("============================================");
    }

    /**
     * Prints the relevant response.
     *
     * @param response the response.
     */
    public void printResponse(Response response) {
        String message = "> ";
        switch (response) {
        case ADD_TASK:
            message += "I've got your back. Adding the new task:";
            break;
        case COMMAND_NOT_RECOGNIZED:
            message += "I don't understand your request.";
            break;
        case DATE_NOT_RECOGNIZED:
            message += "Sorry. I only recognize date in the format YYYY-MM-DD";
            break;
        case DELETE:
            message += "The task has been removed.";
            break;
        case DONE:
            message += "Another task off the list. Good job!";
            break;
        case EMPTY_COMMAND:
            message += "Aren't you a quiet type.";
            break;
        case GOODBYE:
            message += "It's nice talking to you. See you soon! ;)";
            break;
        case IO_ERROR:
            message += "Opps. Something went wrong when saving your tasks.";
            break;
        case INDEX_NAN:
            message += "The index is not a number.";
            break;
        case INVALID_ARGUMENT:
            message += "You cannot set task count to less than zero.";
            break;
        case ITEM_NOT_FOUND:
            message += "That item is not on your list.";
            break;
        case LIST:
            message += "Here's your list:";
            break;
        case MISSING_DATE:
            message += "The date is missing.";
            break;
        case MISSING_DATE_TIME:
            message += "Either the date or time is missing.";
            break;
        case MISSING_DEADLINE:
            message += "When is the deadline?";
            break;
        case MISSING_DELETE_INDEX:
            message += "Which task do you want to delete?";
            break;
        case MISSING_DESCRIPTION:
            message += "What is the task about?";
            break;
        case MISSING_DONE_INDEX:
            message += "Which task have you completed?";
            break;
        case MISSING_END_TIME:
            message += "When does the event end?";
            break;
        case NO_TASK:
            message += "You have nothing to do today.";
            break;
        case NO_TASK_ON_DATE:
            message += "You have nothing to do on that day.";
            break;
        case TASK_COMPLETED:
            message += "You have already done that task.";
            break;
        case TASK_COUNT:
            if (Task.getTotalTaskCount() == 0) {
                message += "You have no more task today.";
            } else {
                message += "Now you've " + Task.getTotalTaskCount() + " task(s) in your list";
            }
            break;
        case TASK_FOUND:
            message += "These are the tasks you are looking for:";
            break;
        case TASK_NOT_FOUND:
            message += "There is no task matching your keyword";
            break;
        }
        System.out.println(message);
    }

    /**
     * Prints the specified task together with it's index in the TaskList.
     * Used only by TaskList to list out all tasks within the list.
     *
     * @param task  the specified task.
     * @param index the respective index.
     */
    protected void printTask(Task task, int index) {
        System.out.println("  " + index + "." + task);
    }

    /**
     * Prints the specified task on the user interface.
     *
     * @param task the specified task.
     */
    public void printTask(Task task) {
        System.out.println("  " + task);
    }
}
