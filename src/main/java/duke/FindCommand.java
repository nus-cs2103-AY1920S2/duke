package duke;

public class FindCommand implements Command {
    private String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString(tasks.findAndPrint(searchPhrase));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
