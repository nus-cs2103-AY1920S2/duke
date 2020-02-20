package duke.logic.commands;
import duke.commons.Task;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{

    private String[] commands;

    public AddCommand(String commandWord, String[] commands) {
        super(commandWord);
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.addTask(commandWord, commands);
        String output = ui.printAddingMessage(tasks, task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            output += ui.showAddingError(e.getMessage());
        }
        return output;
    }
}
