package duke.commands;

import java.util.List;

public class ExceptionCommand implements Command {
    private String message;

    public ExceptionCommand(List<String> details) {
        this.message = details.get(0);
    }

    @Override
    public String execute() {
        return this.message;
    }
}
