package app.core.commands;

import app.core.tasks.TaskManager;
import app.exceptions.BaseException;
import app.util.Pair;

/**
 * An abstract class that defines abstract methods
 * to be implemented in child Command classes.
 */
public abstract class Command {
    /**
     * Abstract execution method that runs the command on the
     * task manager and user interface.
     * @param taskManager The desired task manager
     * @throws BaseException If any error occurs during the execution of the command.
     */
    public abstract Pair execute(TaskManager taskManager) throws BaseException;
}