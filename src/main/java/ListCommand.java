public class ListCommand extends Command {

    ListCommand() {
        super();
    }

    /**
     * Executes list command.
     *
     * @param tasks Task object.
     * @param ui UI object.
     * @param storage Storage object.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Here are the tasks in your list:");
        for (int i = 0; i < tasks.list.size(); i++) {
            Task ob = tasks.list.get(i);
            String tick = (ob.getDone() == 0) ? "[N]" : "[Y]";
            ui.printString(i + 1 + ". " + ob.toString());
        }
    }

    boolean isExit() {
        return false;
    }
}
