package duke.pack;

public class DoneCommand extends Command {
    protected int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum > tasks.getSize()) {
            throw new DukeException("    Oh no! That task does not exist!");
        }

        Task task = tasks.markAsDone(taskNum);
        storage.save(tasks);
        ui.showDone(task);

    }

    public Boolean isExit() {
        return false;
    }

}
