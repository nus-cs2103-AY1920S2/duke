package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for exiting the Duke program.
 *
 * @author Firzan Armani
 */
public class ExitCommand extends Command {
    /**
     * ExitCommand constructor.
     *
     */
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
        ui.dukePrompt("Good bye, boss! Call me if you need me. I'll be waiting!");
        return "Good bye, boss! Call me if you need me. I'll be waiting!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}