package app.core;

import java.util.Scanner;

/**
 * This class represents the User Interface of the Duke 
 * program. The class provides methods to render text on 
 * a custom output.
 */
public final class UserInterface {
    /**
     * The maximum length of the string before word wrapping
     * occurs
     */
    public static final int MAX_STRING_LENGTH = 60;

    private Scanner scanner;
    private boolean isClosed = false;

    /**
     * Initializes a new User Interface object
     */
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the line entered by the user on the console
     * @return the String entered by the user
     */
    public String listen() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a message on the standard output
     * @param message The message to be printed
     */
    public void render(String message) {
        System.out.println(UserInterface.createLinedMessage(message));
    }
    
    /**
     * Prints a message on the standard error
     * @param message The message to be printed
     */
    public void renderError(String message) {
        System.err.println(UserInterface.createLinedMessage(message));
    }
    
    /**
     * Returns a boolean representing whether the UI has closed
     * @return a boolean representing whether the UI has closed
     */
    public boolean isClosed() {
        return this.isClosed;
    }
    
    /**
     * Closes the UI. No further input and output can happen
     * using this UI
     */
    public void close() {
        this.isClosed = true;
    }

    private static String createLinedMessage(String message) {
        StringBuilder output = new StringBuilder();
        output.append("    ____________________________________________________________\n");

        for (String str : message.split("\n")) {
            int index = 0;
            while (index < str.length()) {
                int print_length = Math.min(str.length() - index, MAX_STRING_LENGTH - 2);
                String formattedString = String.format("      %s\n", str.substring(index, index + print_length));
                
                output.append(formattedString);
                index += print_length;
            }
        }
        
        output.append("    ____________________________________________________________\n");
        return output.toString();
    }
}