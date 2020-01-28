package duke.pack;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAdd(task);
        ui.showCount(tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
