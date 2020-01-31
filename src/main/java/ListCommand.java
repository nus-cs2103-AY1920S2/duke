public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            Task curTask = tasks.getTasks().get(i);
            msg += "\n" + (i + 1) + ". " + curTask;
        }
        ui.printMsg(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
