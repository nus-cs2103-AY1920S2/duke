public class EventCommand extends Command {
    protected String spli;
    protected String des;
    protected String date;
    public EventCommand(String spli, String des, String date) {
        this.spli = spli;
        this.des = des;
        this.date = date;
    }
    public void execute(Ui ui, Storage storage, TaskList tasklist) {
        Task t = new Event(getIndex(), date);
        tasklist.addTask(t);
        storage.store(tasklist.getEntireList());
        ui.printTodoComplete(t, tasklist.getTaskListSize());
    }
    public String getIndex() {
        return des;
    }
}
