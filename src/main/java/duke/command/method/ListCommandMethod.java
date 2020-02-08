package duke.command.method;

import duke.Duke;
import duke.command.Command;

public class ListCommandMethod implements CommandMethod {
    public String getCommandName() {
        return "list";
    }

    public String getFormat() {
        return getCommandName();
    }

    public String getDescription() {
        return "Lists all tasks.";
    }

    public void execute(Command command) {
        Duke program = Duke.getProgram();
        program.getUi().print("Tasks so far:\n"
                + program.getTaskList().toString());
    }
}