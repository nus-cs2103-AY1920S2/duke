public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printDividerLine();
        ui.printMessageNoDivider("     Here are the tasks in your list:\n");
        ui.printMessageNoDivider(tasks.toString());
        ui.printDividerLine();
    }
}
