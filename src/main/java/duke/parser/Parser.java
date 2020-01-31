package duke.parser;
import java.util.Scanner;

public class Parser {
    private Parser() {
    }

    public static Command parse(String cmdLine) {
        return new Command(new Scanner(cmdLine));
    }
}