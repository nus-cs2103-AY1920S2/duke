package duke.command;

import duke.Storage;
import duke.TaskList;

public interface Command {

    void execute(TaskList tasks, Storage storage);

    String output();
}
