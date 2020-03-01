package duke;

/**
 * Parses through user input to execute command.
 */
public class Parser {
    protected TaskList task;
    protected Storage storage;
    protected Ui ui;

    Parser(TaskList task,Storage storage,Ui ui) {
        this.task = task;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses through user input to execute correct command.
     * @param str from user input
     * @return Command to execute
     * @throws DukeException when command is empty
     */
    public static Command parse(String str) throws DukeException {
        if  (str.length() == 0) {
            throw new DukeException("Command should not be empty");
        }
        String[] temp = str.split(" ");
        switch (temp[0]) {
        case "todo":
            //System.out.println("Hi2");
            return new AddCommand(str);
        case "deadline":
            return new AddCommand(str);
        case "event":
            return new AddCommand(str);
        case "list":
            return new ListCommand(str);
        case "delete":
            return new DeleteCommand(str);
        case "done":
            return new DoneCommand(str);
        case "bye":
            return new ByeCommand(str);
        default:
            throw new DukeException("Please like pass in an input");
        }
    }


}

