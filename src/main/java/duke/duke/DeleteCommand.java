package duke;

/**
 * Deletes task from tasklist from Duke
 */
public class DeleteCommand extends Command {

    DeleteCommand(String str) {
        super(str);
    }

    /**
     *
     * @param lst from TaskList from Duke
     * @param storage from Storage from Duke
     * @param ui from UI from Duke from Duke
     * @param tasks from TaskNum from Duke
     * @return String containing the delete command returned from ui
     */
    String execute(TaskList lst, Storage storage, Ui ui, TasksNum tasks) {
        int i = Integer.parseInt(str.split(" ")[1]) - 1;
        Task task = lst.getTask(i);
        try {
            lst.deleteTask(task, tasks);
        } catch (DukeException e) {
            System.out.println(e);
        }
        if (!task.getStatus()) {
            tasks.subNum();
        }
        return ui.taskDelete(task, tasks);
    }
}