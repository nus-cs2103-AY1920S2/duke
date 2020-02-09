public class Parser {

    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

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