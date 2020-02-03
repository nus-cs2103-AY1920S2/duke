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
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.list.size(); i++) {
            Task ob = tasks.list.get(i);
            String tick = (ob.getDone() == 0) ? "[N]" : "[Y]";
            s = s + (i + 1 + ". " + ob.toString() + "\n");
        }
        return s;
    }

    boolean isExit() {
        return false;
    }
}
