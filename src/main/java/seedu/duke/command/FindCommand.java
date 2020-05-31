package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.DukeIoException;

public class FindCommand extends Command {

    private final String[] keywords;

    public FindCommand(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException {
        TaskList matchedTasks = tasks.find(keywords);
        ui.printMatchedTaskList(matchedTasks);
    }
}
