package duke.command;

import duke.exception.TaskIndexOutOfBoundException;
import duke.task.TaskList;

import static duke.common.Messages.EMPTY_TASK_LIST_MSG;

class ListCommand implements Command {
    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public boolean isUndoCommand() {
        return false;
    }

    @Override
    public boolean canBeUndone() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        int listSize = taskList.size();
        if (listSize == 0) {
            return new CommandResponse(EMPTY_TASK_LIST_MSG);
        } else {
            StringBuilder responseBuilder = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < listSize; i++) {
                try {
                    responseBuilder.append(i + 1).append('.').append(taskList.get(i)).append('\n');
                } catch (TaskIndexOutOfBoundException e) {
                    assert false : e; // will not reach here
                }
            }
            return new CommandResponse(responseBuilder.toString());
        }
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        throw new AssertionError("Undo for list command will not be called.");
    }
}
