package duke.command.method;

import duke.Duke;
import duke.command.Command;

public class ListCommandMethod implements CommandMethod {
    public static final String NAME = "list";

    public void execute(Command command) {
        Duke program = Duke.getProgram();
        program.getUi().print("Tasks so far:\n"
                + program.getTaskList().toString());
    }
}