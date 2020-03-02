package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for listing the tags currently in the TagList.
 *
 * @author Firzan Armani
 */
public class TagsCommand extends Command {
    /**
     * TagsCommand constructor.
     */
    public TagsCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
        String[] temp;
        if (tagList.getSize() == 0) {
            temp = new String[]{"Boss, no tags here!"};
        } else {
            temp = new String[tagList.getSize() + 1];
            temp[0] = "So I've noted these tags, boss";
            for (int i = 0; i < tagList.getSize(); i++) {
                temp[i + 1] = (i + 1) + ". " + tagList.getTagAt(i).toString();
            }
        }
        ui.dukePrompt(temp);
        return String.join("\n", temp);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}