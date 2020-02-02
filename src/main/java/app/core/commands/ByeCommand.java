package app.core.commands;

import app.core.tasks.TaskManager;
import app.core.UserInterface;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) {
        userInterface.close();
    }
}