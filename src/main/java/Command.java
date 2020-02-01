public interface Command {

    public void execute(String task, Ui ui, Storage storage, TaskList taskList);
}
