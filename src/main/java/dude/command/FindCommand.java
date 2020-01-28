package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        ui.respond(() -> {
            ui.speak("These are the matching tasks I found:");
            for (int i = 1; i <= tasks.taskCount(); i++) {
                if (tasks.getTask(i).getDetails().contains(keyword)) {
                    ui.speak(String.format("%d.%s", i, tasks.getTask(i)));
                }
            }
        });
    }
}
