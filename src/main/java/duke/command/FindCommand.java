package duke.command;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

import duke.exception.KeywordNotFoundException;

public class FindCommand extends Command {
    public FindCommand(String input, boolean isExit) {
        super(input, isExit);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws KeywordNotFoundException {
        return tasks.findKeyword(input);
    }
}