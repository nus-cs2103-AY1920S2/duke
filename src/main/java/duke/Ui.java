package duke;

/**
 * Represents the User Interface in DukeProject.
 */
public class Ui {
    private String logo;
    private String lineBreak;

    /**
     * Prints out the introductory message for DukeProject.
     */
    public void getIntroduction() {
        System.out.println(lineBreak);
        System.out.println("Hello I am \n" + logo
                + "\nWhat can I do for you?");
        System.out.println(lineBreak);
    }

    /**
     * Prints out the exit message for DukeProject.
     */
    public void exit() {
        System.out.println(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }

    /**
     * Prints out a lineBreak.
     */
    public void lineBreak() {
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
