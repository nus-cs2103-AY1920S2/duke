package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;

public class HelpCommand extends Command {

    /**
     * Returns the response to help command.
     *
     * @param ui Used to return Duke's response.
     * @param taskList Unused.
     * @param storage Unused.
     * @param schedule Unused.
     * @return Duke's response to help.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule) {
        return ui.say(SayType.HELP);
    }

}
