public class Parser {
    public String parse(String command) {
        if (command.equals("bye")) {
            return null;
        }
        return command;
    }
}