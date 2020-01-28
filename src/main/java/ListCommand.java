public class ListCommand extends Command {

    ListCommand() {
        super();
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.printString("Here are the tasks in your list:");
        for(int i = 0; i < tasks.list.size(); i++){
            Task ob = tasks.list.get(i);
            String tick = (ob.getDone() == 0) ? "[N]" : "[Y]";
            ui.printString(i + 1 + ". " + ob.toString());
        }
        ui.showLine();
    }

    boolean isExit() {
        return false;
    }
}
