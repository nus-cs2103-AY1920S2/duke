package duke;

/**
 * Follows command to exit programme.
 */
public class
    ByeCommand extends Command {

    ByeCommand(String str) {
        super(str);
    }

    /**
     *Dislays farewell greeting and saves data to harddrive.
     * @param lst from TaskList from Duke
     * @param storage from Storage from Duke
     * @param ui from UI from Duke from Duke
     * @param tasks from TaskNum from Duke
     * @return String containing the bye command returned from ui
     */
    String execute(TaskList lst, Storage storage, Ui ui, TasksNum tasks) {
        storage.writeToFile(lst);
        return ui.saybye();
    }

    @Override
    boolean isExit() {
        return true;
    }

}