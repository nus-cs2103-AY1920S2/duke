package duke.commands;

import duke.exceptions.DukeBadIndexException;
import duke.exceptions.DukeDuplicateTaskException;
import duke.exceptions.DukeWriteFailException;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public abstract class Command {
    public abstract String execute(
            TaskList taskList, Ui ui, Storage storage) throws DukeBadIndexException, DukeWriteFailException,
            DukeDuplicateTaskException;

}
