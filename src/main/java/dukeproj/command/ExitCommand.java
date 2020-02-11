package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command to exit from Duke Application.
     * However, this method primarily only return Duke's response. The actual Exit check will be done by the GUI
     * using the isExit method.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList Unused.
     * @param storage Unused.
     * @param schedule Unused.
     * @return Duke's response in the form of a String.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule) {
        return ui.say(SayType.EXIT);
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return True as the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
