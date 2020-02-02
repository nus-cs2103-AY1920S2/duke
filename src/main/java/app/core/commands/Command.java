package app.core.commands;

import app.core.tasks.TaskManager;
import app.exceptions.BaseException;
import app.core.UserInterface;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, UserInterface userInterface) throws BaseException;
}