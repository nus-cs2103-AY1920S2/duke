package duke.parser;
import java.util.Scanner;

public class Parser {
    private Parser() {
    }

    /**
     * Parse new Command object based on one command
     * @param cmdLine = user command
     * @return new Command object
     */
    public static Command parse(String cmdLine) {
        return new Command(new Scanner(cmdLine));
    }
}