package duke.pack;

/**
 * Represents a command to exit the bot.
 */
public class ExitCommand extends Command {
    /**
     * Creates an exit command.
     */
    public ExitCommand() {

    }

    /**
     * Executes the exit command and stops the bot.
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @throws DukeException if tasks cannot be saved to hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        ui.exit();
    }


    /**
     * Executes exit command and gets response for the GUI
     * @param tasks TaskList containing all the added tasks
     * @param ui UI that handles interactions with user
     * @param storage Storage that handles updating of tasks in hard disk
     * @return String response for GUI
     * @throws DukeException if tasks cannot be saved to hard disk
     */
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        String resp = "See you later! :)";

        return resp;
    }


    /**
     * Indicates whether command is exit.
     * @return boolean true if it is an exit command, else false
     */
    public Boolean isExit() {
        return true;
    }

}
