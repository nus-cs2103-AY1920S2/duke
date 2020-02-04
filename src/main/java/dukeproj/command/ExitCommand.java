package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;

public class ExitCommand extends Command {
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender) {
        return ui.say(SayType.EXIT);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
