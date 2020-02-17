package duke.command;

import java.time.LocalDateTime;

import duke.exception.TaskIndexOutOfBoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import static duke.common.Messages.generateAddSuccessMessage;
import static duke.common.Messages.generateDeleteSuccessMessage;

class AddCommand implements Command {
    static final int TYPE_TODO = 1;
    static final int TYPE_DEADLINE = 2;
    static final int TYPE_EVENT = 3;

    private final Task taskToAdd;
    private boolean isExecuted;
    private int addedTaskIndex;

    AddCommand(int type, String description, LocalDateTime time) {
        addedTaskIndex = -1;
        isExecuted = false;
        switch (type) {
        case TYPE_TODO:
            taskToAdd = new Todo(description);
            break;
        case TYPE_DEADLINE:
            taskToAdd = new Deadline(description, time);
            break;
        case TYPE_EVENT:
            taskToAdd = new Event(description, time);
            break;
        default:
            throw new AssertionError("CommandParser will ensure the correct type is passed into the constructor.");
        }
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
        return true;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        assert !isExecuted : "Executing already executed command.";
        addedTaskIndex = taskList.size();
        taskList.add(taskToAdd);
        isExecuted = true;
        return new CommandResponse(generateAddSuccessMessage(taskToAdd.toString(), taskList.size()));
    }

    @Override
    public CommandResponse undo(TaskList taskList) {
        assert isExecuted : "Undoing not executed command.";
        try {
            taskList.remove(addedTaskIndex);
        } catch (TaskIndexOutOfBoundException e) {
            assert false : e; // will not reach here
        }
        addedTaskIndex = -1;
        isExecuted = false;
        return new CommandResponse(generateDeleteSuccessMessage(taskToAdd.toString(), taskList.size()));
    }
}
