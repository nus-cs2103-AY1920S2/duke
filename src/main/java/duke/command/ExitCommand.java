package duke.command;

import duke.exception.DukeException;
import duke.io.Serializer;
import duke.io.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Start the cleanup and exit process of Duke.
     * Serializes the input TaskList to file, and print a goodbye message.
     *
     * @param taskList The TaskList to serialize to file.
     * @param ui The Ui used to print any notifications.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Serializer.serialize(taskList);
        ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
