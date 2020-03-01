package commands;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exception.InvalidIndexException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int[] arrayOfIndexes;

    public DeleteCommand(int[] arrayOfDeleteIndexes) {
        super();
        this.arrayOfIndexes = arrayOfDeleteIndexes;
    }

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
