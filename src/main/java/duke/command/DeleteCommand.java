package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList taskList, Storage storage) {
        return taskList.deleteTask(this.index, storage);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof DeleteCommand) {
            DeleteCommand castedDeleteCommand = (DeleteCommand)object;
            return this.index == castedDeleteCommand.index;
        } else {
            return false;
        }
    }
}
