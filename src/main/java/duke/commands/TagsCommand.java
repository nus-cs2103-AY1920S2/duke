package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class TagsCommand extends Command {
    public TagsCommand() {
        super();
    }

	@Override
	public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
		return null;
	}

	@Override
	public boolean isExit() {
		return false;
	}
}