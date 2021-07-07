package command;

import common.Message;
import common.Storage;
import task.TaskList;
import exception.DukeException;
import ui.TextUi;

import java.util.ArrayList;

/**
 * Represents a command that displays the helping page.
 */
public class HelpCommand extends Command {

    protected ArrayList<String> commandList;

    /**
     * Constructor of the HelpCommand class.
     */
    public HelpCommand() {
        super();
        commandList = new ArrayList<String>();
        commandList.add("todo [NAME] : add a new todo task with name");
        commandList.add("event [NAME] /at [TIME] : add a new event task with name and time");
        commandList.add("deadline [NAME] /by [TIME] : add a deadline task with name and time");
        commandList.add("done [INDEX] : mark the task at the position of [INDEX] as done");
        commandList.add("delete [INDEX] : remove the task at the position of [INDEX]");
        commandList.add("list : show all current tasks with status");
        commandList.add("reminders : show all current tasks that are undone");
        commandList.add("reminders todo : show all the current todo tasks that are undone");
        commandList.add("reminders event : show all the current events that are undone");
        commandList.add("reminders deadline : show all the current deadlines that are undone");
        commandList.add("find [KEYWORD] : search for and display all the tasks that contain the [KEYWORD]");
        commandList.add("help : show this helping sheet");
        commandList.add("bye (optional)[ANY POSTFIX] : exit the system");
    }

    /**
     * Converts the list of command illustration to a string.
     *
     * @return a string consists of all the command illustration
     */
    public String toHelpingList() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < this.commandList.size() - 1; i++) {
            sb.append(this.commandList.get(i) + "\n");
        }
        sb.append(this.commandList.get(this.commandList.size() - 1));
        return sb.toString();
    }

    /**
     * Executes the "showing helping page" type of commands.
     *
     * @param tasks   A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     */
    public String execute(TaskList tasks, TextUi textUi, Storage storage) {
        return textUi.showHelpingPage(this.toHelpingList());
    }

    /**
     * Informs the user that help command cannot be undone.
     *
     * @param tasks A TaskList containing all tasks
     * @param textUi a TextUi object that handles user-system interaction
     * @param storage A Storage object which specifies the location of the data
     * @return a string representing the result of undoing the previous commmand
     * @throws DukeException when invalid input is detected
     */
    public String undo(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        return textUi.showError_Str(Message.MESSAGE_CANNOTUNDO);
    }

    /**
     * Returns whether the current command is an exit command.
     *
     * @return a boolean value representing the property of the current command
     */
    public boolean isExit() {
        return false;
    }

    public boolean isUndoable() {
        return false;
    }

}