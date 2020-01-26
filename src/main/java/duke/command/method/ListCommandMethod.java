package duke.command.method;

import duke.Duke;
import duke.command.Command;

public class ListCommandMethod implements CommandMethod {
    public static final String NAME = "list";

    public String execute(Duke program, Command command) { 
        return program.getTaskList().toString();
    }
}