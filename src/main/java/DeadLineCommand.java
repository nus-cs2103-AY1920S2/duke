import java.time.LocalDateTime;

public class DeadLineCommand extends Command {
    protected String spli;
    protected String des;
    protected LocalDateTime fin;
    public DeadLineCommand(String spli, String des, LocalDateTime fin) {
        this.spli = spli;
        this.des = des;
        this.fin = fin;
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        Task t = new Deadline(getIndex(), fin);
        tasklist.addTask(t);
        storage.store(tasklist.getEntireList());
        ui.printTodoComplete(t, tasklist.getTaskListSize());
    }
    public String getIndex() {
        return des;
    }
}
