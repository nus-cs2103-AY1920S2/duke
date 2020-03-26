package duke.commands;

import duke.model.Storage;
import duke.model.TaskList;

public class HelloCommand extends Command {
    private static final String MESSAGE = "Hello!! <3 <3";
    
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return MESSAGE;
    }
}
