package dukeproj;

import dukeproj.enums.SayType;

/**
 * Represents the User Interface in DukeProject.
 */
public class Ui {
    private String logo;
    private String lineBreak;

    public String say(SayType type) {
        switch (type) {
        case INTRO:
            return "Hello! I am\n" + logo + "\nWhat can I do for you today?";
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
        default:
            return "";
        }
    }

    /**
     * Prints out the introductory message for DukeProject.
     */
    public String getIntroduction() {
        return lineBreak + "\nHello I am \n" + logo + "\nWhat can I do for you?\n" + lineBreak;
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public String getExit() {
        return lineBreak + "\nBye! Hope to see you again soon!\n" + lineBreak;
    }

    /**
     * Prints out a lineBreak.
     */
    public void printLineBreak() {
        System.out.println(lineBreak);
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
        lineBreak = "_____________________________" +
                "_________________________";

    }
}
