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
            return "I have added the following task: ";
        case DONE:
            return "I have marked the following task as done: ";
        case DELETE:
            return "I have deleted the following task: ";
        case FIND:
            return "Here are your matching tasks: ";
        case SEARCH:
            return "Here are your tasks on the date of ";
        case INVALID_COMMAND:
            return "Oi, there's no such command!";
        case EMPTY_DESCRIPTION:
            return "You blockhead! You forgot to include a description!";
        case BAD_DATE:
            return "HEY! Bad date format!\nMake sure the format is: dd mm yy";
        case BAD_DESCRIPTION:
            return "Tsk! ";
        case HELP:
            return getHelp();
        default:
            return "**** What is this!?";
        }
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public String getExit() {
        return "Get Lost.";
    }

    private String getHelp() {
        return "Here are the commands, you better follow them\n"
                + "\nList: returns a list of all your tasks\n"
                + "\nTodo: makes a new todo task\n  Format: todo (task)\n"
                + "\nDeadline: makes a new deadline task\n  Format: deadline (task) /by (dd mm yy)\n"
                + "\nEvent: makes a new event task\n  Format: event (task) /at (dd mm yy)\n"
                + "\nDone: marks a task as done\n  Format: done (task index)\n"
                + "\nDelete: deletes a task\n  Format: delete (task index)\n"
                + "\nSchedule: view schedule for a specific date\n  Format: schedule (dd mm yy)\n"
                + "\nFind: find tasks using keywords\n  Format: find (keywords)\n"
                + "\nBye: exits duke";
    }

    private String getGuiIntroduction() {
        return "Hello! This is\n" + logo
                + "\nYou can type help for a list of commands!"
                + "\nSo, whatdya want?";
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
