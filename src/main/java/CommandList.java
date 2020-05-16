import java.util.ArrayList;

public class CommandList extends Command {

    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {

        StringBuilder message = new StringBuilder();
        message.append("Current task list\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task curTask = tasks.get(i);
            String displayString = String.format("%d. [%s] [%s] %s\n",
                i + 1,
                curTask.getType(),
                curTask.getDoneIcon(),
                curTask.getDisplayName()
            );
            message.append(displayString);
        }
        return message.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
