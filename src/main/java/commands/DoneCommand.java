package commands;

import java.io.IOException;
import java.util.stream.IntStream;
import exception.InvalidIndexException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    private int[] arrayOfIndexes;

    /**
     * Constructs the done command instance.
     *
     * @param arrayOfDoneIndexes is the array of inputted done indexes.
     */
    public DoneCommand(int[] arrayOfDoneIndexes) {
        super();
        this.arrayOfIndexes = arrayOfDoneIndexes;
    }

    /**
     * Executes the done command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return tasks marked as done response to user.
     * @throws InvalidIndexException is exception when dealing with index.
     * @throws IOException is exception for file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidIndexException {
        int[] arrayOfDoneIndexes = IntStream.of(this.arrayOfIndexes)
                                            .filter(index -> tasks.getTaskListSize() > index && index >= 0)
                                            .toArray();

        IntStream.of(arrayOfDoneIndexes)
                 .forEach(index -> tasks.getTask(index).markAsDone());

        storage.saveTasksIntoFile(tasks);

        return ui.acknowledgeDone(tasks, arrayOfDoneIndexes);
    }
}
