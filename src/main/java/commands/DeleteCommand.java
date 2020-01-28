package commands;

import storage.Storage;
import taskList.TaskList;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    private int index;

    public DeleteCommand(int index) {
        super(index);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            System.out.println("Successfully Removed: " + tasks.getList().get(this.index-1));
            tasks.remove(this.index - 1);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Index does not exist");
        }
    }
}
