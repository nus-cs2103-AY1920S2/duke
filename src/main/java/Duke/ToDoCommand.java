package Duke;
public class ToDoCommand extends Command {
    protected String spli;
    public ToDoCommand(String spli) {
        this.spli = spli;
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        Task t = new Todo(getIndex());
        tasklist.addTask(t);
        storage.store(tasklist.getEntireList());
        ui.printTodoComplete(t, tasklist.getTaskListSize());
    }
    public String getIndex() {
        return spli;
    }
}
