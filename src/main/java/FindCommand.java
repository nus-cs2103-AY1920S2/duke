import java.util.ArrayList;

public class FindCommand extends Command {
    ArrayList<Task> helper = new ArrayList<>();
    TaskList matching = new TaskList(helper);
    protected String keyword;

    public FindCommand(String command, String keyword) {
        this.command = command;
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (Task t : tasks.getTasks()) {
            if (t.contains(this.keyword)) {
                this.matching.add(t);
            }
        }
        ui.showFindTasks(this.matching);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
