package duke;

/**
 * Command for deleting a task.
 */
public class DeleteCommand extends Command {

    DeleteCommand(String input){
        super(input);
    }

    /**
     * Parses the index and finds the task to be deleted and deletes it accordingly.
     * @param tasks from the TaskList initialised from duke.
     * @param ui from the UI initialised from duke.
     * @param storage from the Storage initialised from duke.
     * @throws DukeException from saveData.
     */
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.parseInt(strArr[1]) - 1;
        Task taskToBeDeleted = tasks.delete(index);
        ui.printWithFormat(taskToBeDeleted.toString(), "delete", tasks);
        storage.saveData(tasks);
    }

}
