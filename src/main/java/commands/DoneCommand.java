package commands;

import java.io.IOException;
import java.util.stream.IntStream;

import exception.InvalidIndexException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DoneCommand extends Command {
    private int[] arrayOfIndexes;

    public DoneCommand(int[] arrayOfDoneIndexes) {
        super();
        this.arrayOfIndexes = arrayOfDoneIndexes;
    }

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
