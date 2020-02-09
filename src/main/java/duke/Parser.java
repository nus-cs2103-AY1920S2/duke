package duke;

/**
 * The type Parser.
 */
public class Parser {

    private Ui ui;

    /**
     * Instantiates a new Parser.
     *
     * @param ui the ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses command given by the user.
     *
     * @param command the command
     * @return the command
     * @throws DukeException the duke exception
     */
    public Command parseCommand(String command) throws DukeException {
        if (command.contains("deadline")) {
            return new AddCommand();
        }
        else if(command.contains("event")) {
            return new AddCommand();
        }
        else if(command.contains("todo")) {
            return new AddCommand();
        }
        else if(command.contains("bye")) {
            return new ByeCommand();
        }
        else if(command.contains("done")) {
            return new DoneCommand();
        }
        else if(command.contains("delete")) {
            return new DeleteCommand();
        }
        else if(command.contains("list")) {
            return new ListCommand();
        }
        else {
            throw new DukeException(ui.showWrongCommandError());
        }

    }

}