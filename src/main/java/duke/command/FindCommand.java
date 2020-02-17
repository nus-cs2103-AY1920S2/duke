package duke.command;

import duke.exception.TaskIndexOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

import static duke.common.Messages.EMPTY_FIND_RESULT_MSG;

class FindCommand implements Command {
    private final String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

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
        int count = 0;
        StringBuilder responseBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < listSize; i++) {
            try {
                Task currentTask = taskList.get(i);
                if (currentTask.descriptionContains(keyword)) {
                    count++;
                    responseBuilder.append(i + 1).append('.').append(currentTask).append('\n');
                }
            } catch (TaskIndexOutOfBoundException e) {
                assert false : e;
            }
        }

        if (count == 0) {
            return new CommandResponse(EMPTY_FIND_RESULT_MSG);
        }
        return new CommandResponse(responseBuilder.toString());
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        throw new AssertionError("Undo for find command will not be called.");
    }
}
