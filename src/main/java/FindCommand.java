import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks.getTaskList()) {
            if (t.getTaskName().contains(keyword)) {
                foundTasks.add(t);
            }
        }
        ui.printFoundTasks(foundTasks);
    }

    public boolean isExit() {
        return false;
    }

}
