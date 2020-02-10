public class ListCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.printList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
