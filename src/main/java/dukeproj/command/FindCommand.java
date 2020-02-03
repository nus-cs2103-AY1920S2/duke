package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.DukeDescriptionException;

public class FindCommand extends Command {
    private String description;

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender)
            throws DukeDescriptionException {
        if (description.isEmpty()) {
            throw new DukeDescriptionException("Empty Description");
        }
        TaskList outputList = new TaskList(taskList.find(description.split(" ")));
        return ui.say(SayType.FIND) + "\n" + taskList.toString();
    }

    public FindCommand(String description) {
        this.description = description;
    }
}
