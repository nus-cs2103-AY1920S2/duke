package dukeproj;

import dukeproj.enums.SayType;

/**
 * Represents the User Interface in DukeProject.
 */
public class Ui {
    /** Logo of Duke. */
    private String logo;

    /**
     * Returns Duke's response to a certain SayType.
     *
     * @param type The type of response to be returned.
     * @return Duke's response.
     */
    public String say(SayType type) {
        switch (type) {
        case INTRO:
            return getGuiIntroduction();
        case EXIT:
            return getExit();
        case LIST:
            return "Here are all your tasks: ";
        case ADD:
            return "Okay! I have added the following task: ";
        case DONE:
            return "Okay! I have marked the following task as done: ";
        case DELETE:
            return "Okay! I have deleted the following task: ";
        case FIND:
            return "Here are your matching tasks: ";
        case SEARCH:
            return "Here are your tasks on the date of ";
        case INVALID_COMMAND:
            return "Sorry! I do not know what this command means!";
        case EMPTY_DESCRIPTION:
            return "OOPS! You forgot to include a description!";
        case BAD_DATE:
            return "Sorry I don't recognise this date format!\nPlease make sure the format is: dd mm yy";
        case BAD_DESCRIPTION:
            return "OOPS! ";
        case HELP:
            return getHelp();
        default:
            return "**** Duke does not know what to say!";
        }
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public String getExit() {
        return "Bye! Hope to see you again soon!";
    }

    private String getHelp() {
        return "Here are the list of commands:\nList: returns a list of all your tasks\n"
                + "Todo: makes a new todo task\n  Format: todo (task)\n"
                + "Deadline: makes a new deadline task\n  Format: deadline (task) /by (dd mm yy)\n"
                + "Event: makes a new event task\n  Format: event (task) /at (dd mm yy)\n"
                + "Done: marks a task as done\n  Format: done (task index)\n"
                + "Delete: deletes a task\n  Format: delete (task index)\n"
                + "Schedule: view schedule for a specific date\n  Format: schedule (dd mm yy)\n"
                + "Find: find tasks using keywords\n  Format: find (keywords)\n"
                + "Bye: exits duke";
    }

    private String getGuiIntroduction() {
        return "Hello! I am\n" + logo
                + "\nPlease type help for a list of commands!"
                + "\nWhat can I do for you today?";
    }

    /**
     * Creates the User Interface with pre-defined logo and linebreak.
     */
    public Ui() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }
}
