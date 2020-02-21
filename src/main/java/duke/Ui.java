package duke;

import java.util.Scanner;

/** Class which handles interactions with the user such as input of commands and printing of result logs. */
public class Ui {
    static final String BORDER = "    ____________________________________________________________";

    Scanner in = new Scanner(System.in);
    String arguments;

    /**
     * Checks if there are further commands to be inputted by the user
     * @return true if further inputs exist
     */
    public boolean hasNextInput() {
        return in.hasNext();
    }

    /**
     * Reads the next command from the input
     * @return a String containing a full command
     */
    public String getLine() {
        return in.nextLine();
    }

    /**
     * Prints out a log line-by-line with indentation and borders
     * @param ss the list of strings to be printed.
     */
    public void out(String... ss) {
        
        System.out.println(BORDER);
        for (String s : ss) {
            System.out.println("    " + s);
        }
        System.out.println(BORDER);
    }

    /** Shuts down the Ui object by closing all existing streams. */
    public void close() {
        in.close();
    }

	public String respond(String[] ss) {
        String response = "";
        for (String s : ss) {
            // System.out.println(s);
            response = response.concat(s + "\n");
        }
        return response;
	}
}