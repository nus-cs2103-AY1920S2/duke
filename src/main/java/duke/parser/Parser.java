package duke.parser;

import java.util.Scanner;

public class Parser {
    private Parser() {
    }

    /**
     * Parse new Command object based on one command
     *
     * @param cmdLine = user command
     * @return new Command object
     */
    public static Command parse(String cmdLine) {
        Scanner sc = new Scanner(cmdLine);
        String first = sc.next();
        switch (first) {
            case "todo":
            case "deadline":
            case "event":
                return new AddTaskCommand(first, sc);
            case "list":
                return new ListCommand(sc);
            case "delete":
                return new DeleteCommand(sc);
            case "find":
                return new FindCommand(sc);
            case "done":
                return new MarkAsDoneCommand(sc);
            case "undo":
                return new ReverseCommand(sc);
            case "stats":
                return new StatCommand(sc);
            case "exit":
                return new ExitCommand(sc);
            default:
                return new Command(sc);
        }
    }
}