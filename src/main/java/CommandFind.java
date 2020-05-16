import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommandFind extends Command {
    private String pattern;

    public CommandFind(String pattern) {
        this.pattern = pattern;
    }

    private boolean isTaskMatch(Task task) {
        String taskName = task.getName();
        return taskName.contains(pattern);
    }
    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {

        ArrayList<Task> matchTasks = new ArrayList<Task>();
        for (Task task : tasks.getTasks()) {
            if (isTaskMatch(task)) {
                matchTasks.add(task);
            }
        }

        StringBuilder message = new StringBuilder();

        message.append("Matching tasks:\n");
        for (int i = 0; i < matchTasks.size(); i++) {
            message.append(String.format("%d. %s",
                    i + 1,
                    matchTasks.get(i).getDisplayName())
            );
        }
        return message.toString();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
