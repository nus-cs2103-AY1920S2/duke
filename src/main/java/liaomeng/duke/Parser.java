package liaomeng.duke;

import java.util.Scanner;

/**
 * The class that scans user input and convert it into a String array.
 */
public class Parser {
    Scanner sc;

    public Parser() {
        sc = new Scanner(System.in);
    }

    /**
     * Converts user input into a String array by splitting around white space (i.e. " ") and returns the array.
     */
    public String[] parse(String input) {
        return input.split(" ");
    }
}
