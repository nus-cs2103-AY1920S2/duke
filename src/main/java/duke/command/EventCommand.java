package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    private String[] args = new String[0];

    public EventCommand(String[] args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //check if the argument length required is correct
            if (args.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!! The date of a "
                        + "event cannot be empty.");
            }

            //add a new event task to the list
            Task newEventTask = new Event(args[0], args[1]);
            tasks.addTask(newEventTask);

            //print success message
            ui.printMessage(String.format("     Got it. I've added this task:\n       %s\n"
                    + "     Now you have %d tasks in the list.\n", newEventTask,tasks.getSize()));

            //update save file
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
