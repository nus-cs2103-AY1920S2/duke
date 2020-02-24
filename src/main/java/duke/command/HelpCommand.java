package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
/**
 * Represents the command of displaying all the available commands in duke. A <code>ByeCommand</code> object corresponds
 * to the command of displaying the tasks.
 */

public class HelpCommand extends Command {
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */

    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Displays all the commands in Duke . If storage file is invalid, an error message is returned.
     * @param tasks The task list to be updated.
     * @param ui The user interface to be to be shown.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String intro = "Here are the available commands:\n\n";
        String todo = "1) todo: To add a todo task, enter the command todo followed by the description of the "
                + "task.\n" + "     For example: todo go for a run\n\n";
        String event = "2) event: To add an event task, enter the command event followed by the description of "
                + "the task, followed by /at and then finally the date of the event. Take note that the date should be "
                + "entered in this format: YYYY-MM-DD.\n" + "     For example: event project meeting /at   "
                + "2020-12-12\n\n";
        String deadline = "3) deadline: To add a deadline task, enter the command deadline followed by the description "
                + "of the task, followed by /by and then finally the date of the event. Take note that the date should "
                + "be entered in this format: YYYY-MM-DD.\n" + "     For example: event return book /at 2020-09-09\n\n";
        String delete = "4) delete: To delete a task from the task list, enter the command delete followed by the "
                + "index of the task to be deleted.\n" + "     For example: delete 3\n\n";
        String done = "5) done: To mark a task as done, enter the command done followed by the index of the task to "
                + "be marked as done.\n" + "     For example: done 3\n\n";
        String list = "6) list: To see all the tasks in the task list, enter the list command.\n"
                + "     For example: list\n\n";
        String bye = "7) bye: To terminate the program, enter the bye command.\n" + "     For example: bye";
        System.out.println(intro + todo + event + deadline + delete + done + list + bye);
    }
}
