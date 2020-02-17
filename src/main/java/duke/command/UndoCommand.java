package duke.command;

import duke.task.TaskList;

class UndoCommand implements Command {
    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public boolean isUndoCommand() {
        return true;
    }

    @Override
    public boolean canBeUndone() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        throw new AssertionError("execute for undo command will not be called.");
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        throw new AssertionError("Undo for undo command will not be called.");
    }
}
