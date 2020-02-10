package duke.commands;



public class ByeCommand implements Command {
    /**
     * Creates a ByeCommand that returns exit message.
     */
    public ByeCommand() {
    }

    @Override
    public String execute() {
        return "Goodbye!";
    }
}
