package commands;

import java.io.IOException;
import java.util.stream.IntStream;
import exception.InvalidIndexException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int[] arrayOfIndexes;

    /**
     * Constructs the DeleteCommand instance.
     *
     * @param arrayOfDeleteIndexes is the array of inputted delete indexes.
     */
    public DeleteCommand(int[] arrayOfDeleteIndexes) {
        super();
        this.arrayOfIndexes = arrayOfDeleteIndexes;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return deleted tasks response to user.
     * @throws InvalidIndexException is the exception if the inputted index is invalid.
     * @throws IOException is exception for file handling.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException, IOException {
        int[] arrayOfDeleteIndexes =
                IntStream.of(arrayOfIndexes)
                         .filter(index -> tasks.getTaskListSize() > index && index >= 0)
                         .toArray();

        String s = ui.acknowledgeDelete(tasks, arrayOfDeleteIndexes);

        tasks.removeTasks(arrayOfDeleteIndexes);
        storage.saveTasksIntoFile(tasks);

        String taskListCount = ui.currentTaskListSize(tasks);

        return s + "\n" + taskListCount;
    }
}
