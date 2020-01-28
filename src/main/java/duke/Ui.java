package duke;

/**
 * Represents the User Interface in DukeProject.
 */
public class Ui {
    private String logo;
    private String linebreak;

    /**
     * Prints out the introductory message for DukeProject.
     */
    public void introduction() {
        System.out.println(linebreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(linebreak);
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public void exit() {
        System.out.println(linebreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(linebreak);
    }

    /**
     * Prints out a lineBreak.
     */
    public void lineBreak() {
        System.out.println(linebreak);
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
        linebreak = "_____________________________" +
                "_______________________________";
    }
}
