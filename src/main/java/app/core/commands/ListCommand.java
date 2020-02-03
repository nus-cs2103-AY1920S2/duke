package app.core.commands;

import app.core.tasks.TaskManager;
import app.core.UserInterface;

final class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) {
        String output = taskManager.toString();
        userInterface.render(output);
    }
}