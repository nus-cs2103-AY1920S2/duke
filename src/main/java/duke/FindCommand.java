package duke;

public class FindCommand implements Command {
    private String searchPhrase;

    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.find(searchPhrase);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
