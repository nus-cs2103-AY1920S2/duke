package duke.command;

import duke.io.Serializer;
import duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Start the cleanup and exit process of Duke.
     * Serializes the input TaskList to file, and print a goodbye message.
     *
     * @param taskList The TaskList to serialize to file.
     */
    @Override
    public void execute(TaskList taskList) {
        Serializer.serialize(taskList);
        System.out.println("Bye. Hope to never see you again!");
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
