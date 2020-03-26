package duke.duke;

/**
 * Changes status of Task inside tasklist from Duke.
 */
public class DoneCommand extends Command {

    DoneCommand(String str) {
        super(str);
    }

    /**
     * Marks task as done and displays done command.
     * @param lst from TaskList from Duke
     * @param storage from Storage from Duke
     * @param ui from UI from Duke from Duke
     * @param tasks from TaskNum from Duke
     * @return String containing the done command returned from ui
     */
    String execute(TaskList lst, Storage storage, Ui ui, TasksNum tasks) {
        int space = Integer.parseInt(str.split(" ")[1]) - 1;
        Task task = lst.getTask(space);
        task.doAct();
        tasks.subNum();
        return ui.taskDone(task, tasks);
    }
}