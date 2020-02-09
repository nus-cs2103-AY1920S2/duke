package duke.commands;

public class ByeCommand implements Command {
    public ByeCommand() {

    }

    @Override
    public String execute() {
        return "Goodbye!";
    }
}
