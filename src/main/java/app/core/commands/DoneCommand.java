package app.core.commands;

import app.core.UserInterface;
import app.core.tasks.TaskManager;

import app.exceptions.InvalidTaskIndexException;
import app.exceptions.WrongUsageException;

final class DoneCommand extends Command {
    private int taskIndex;

    DoneCommand(String args) throws WrongUsageException {
        try {
            this.taskIndex = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new WrongUsageException("Usage: done <task_index>");
        }
    }

    @Override
    public void execute(TaskManager taskManager, UserInterface userInterface) throws InvalidTaskIndexException {
        try {
            String output = taskManager.setTaskDone(this.taskIndex);
            userInterface.render(output);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(
                "Invalid task index. Please refer to the 'list' command for available indices."
            );
        }
    }
}