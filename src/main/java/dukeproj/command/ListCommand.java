package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;

public class ListCommand extends Command {

    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender) {
        return ui.say(SayType.LIST) + "\n" + taskList.toString();
    }
}
