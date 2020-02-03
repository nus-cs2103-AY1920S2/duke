package app.core;

import java.util.Scanner;

public final class UserInterface {
    public static final int MAX_STRING_LENGTH = 60;

    private Scanner scanner;
    private boolean isClosed = false;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public String listen() {
        return this.scanner.nextLine();
    }

    public void render(String message) {
        System.out.println(UserInterface.createLinedMessage(message));
    }
    
    public void renderError(String message) {
        System.err.println(UserInterface.createLinedMessage(message));
    }
    
    public boolean isClosed() {
        return this.isClosed;
    }
    
    public void close() {
        this.isClosed = true;
    }

    private static String createLinedMessage(String message) {
        StringBuilder output = new StringBuilder();
        output.append("    ____________________________________________________________\n");

        for (String str : message.split("\n")) {
            int index = 0;
            while (index < str.length()) {
                int printLength = Math.min(str.length() - index, MAX_STRING_LENGTH - 2);
                String formattedString = String.format("      %s\n", str.substring(index, index + printLength));
                
                output.append(formattedString);
                index += printLength;
            }
        }
        
        output.append("    ____________________________________________________________\n");
        return output.toString();
    }
}