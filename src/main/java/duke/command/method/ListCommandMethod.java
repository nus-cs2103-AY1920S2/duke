package duke.command.method;

import duke.Duke;
import duke.command.Command;

public class ListCommandMethod implements CommandMethod {
    public static final String NAME = "list";

    public void execute(Duke program, Command command) {
        String message = program.getTaskList().toString();
        program.getUi().print(message);
    }
}