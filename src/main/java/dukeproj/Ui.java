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
            return getIntroduction();
            break;
        case EXIT:
            return getExit();
            break;
        case LIST:
            return ""
            break;
        case ADD:
            break;
        case DONE:
            break;
        case DELETE:
            break;
        case FIND:
            break;
        case SEARCH:
            break;
        case INVALID_COMMAND:
            return "Sorry! I do not know what this command means!";
        case EMPTY_DESCRIPTION:
            return "OOPS! You forgot to include a description!";
        case BAD_DATE:
            return "Sorry I don't recognise this date format!\nPlease make sure the format is: dd mm yy";
        case BAD_DESCRIPTION:
            return "OOPS! ";
        default:
            break;
        }
    }

    /**
     * Prints out the introductory message for DukeProject.
     */
    public String getIntroduction() {
        return lineBreak + "\nHello I am \n" + logo + "\nWhat can I do for you?\n" + lineBreak;
        /*System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);*/
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public String getExit() {
        return lineBreak + "\nBye! Hope to see you again soon!\n" + lineBreak;
/*        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);*/
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
                "_______________________________";

    }
}
