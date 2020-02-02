package app.util;

public class Parser {
    public static StringPair parse(String input) {
        String command = input;
        String args = "";
        
        int splitIndex = input.indexOf(" ");
        if (splitIndex != -1) {
            command = input.substring(0, splitIndex);
            args = input.substring(splitIndex + 1);
        }

        return new StringPair(command.trim(), args.trim());
    }
}