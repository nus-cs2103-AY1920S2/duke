public class ExitCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.printBye();
        try {
            storage.writeList(tasks);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isExit() {
        return true;
    }
}
