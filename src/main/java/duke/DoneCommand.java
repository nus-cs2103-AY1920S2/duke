package duke;

/**
 * Command for setting a task as done.
 */
public class DoneCommand extends Command {

    DoneCommand(String input){
        super(input);
    }

    /**
     * Parses out the index, find the task and sets it as done.
     * @param tasks from Tasklist initialised from duke.
     * @param ui from UI initialised from duke.
     * @param storage from storage initialised from duke.
     * @throws DukeException might throw DukeException from saveData.
     */
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.parseInt(strArr[1]) - 1;
        Task taskToBeDone = tasks.get(index);
        taskToBeDone.setDone();
        ui.printWithFormat(taskToBeDone.toString(), "done", tasks);
        storage.saveData(tasks);
    }
}
