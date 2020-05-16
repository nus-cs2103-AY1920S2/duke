import java.util.Comparator;

public class CommandSort extends Command {
    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {
        tasks.sort(Comparator.comparing(Task::getName));
        return "Task list is sorted";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
