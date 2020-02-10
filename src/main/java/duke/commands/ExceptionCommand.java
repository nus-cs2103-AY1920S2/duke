package duke.commands;

import java.util.List;

public class ExceptionCommand implements Command {
    private String message;

    /**
     * Creates an ExceptionCommand that returns the
     * exception message.
     * @param details Exception message.
     */
    public ExceptionCommand(List<String> details) {
        this.message = details.get(0);
    }

    @Override
    public String execute() {
        return this.message;
    }
}
