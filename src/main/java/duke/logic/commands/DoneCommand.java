package duke.logic.commands;
import duke.commons.Task;
import duke.commons.exceptions.InvalidIndexException;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private int doneIndex;

    public DoneCommand(String commandWord, int doneIndex) {
        super(commandWord);
        this.doneIndex = doneIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        Task task = tasks.getTask(this.doneIndex);
        tasks.markTaskAsDone(this.doneIndex);
        String output = ui.printDoneMessage(task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            output += ui.showMarkingAsDoneError(e.getMessage());
        }
        return output;
    }
}
