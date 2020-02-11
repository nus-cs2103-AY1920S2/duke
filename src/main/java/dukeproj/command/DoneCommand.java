package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;

/**
 * Represents a command to mark a certain task as done.
 */
public class DoneCommand extends Command {
    /** The index of the task to be marked as done. */
    private String index;

    /**
     * Executes the done command to mark the task, as specified in the description, as done.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList The list of tasks to be marked from.
     * @param storage The object to assist in writing the task list into the storage file.
     * @param schedule Duke's schedule to be modified if done task is date sensitive.
     * @return Duke's response in the form of a String.
     * @throws BadDescriptionException If index is not in the form of an integer.
     * @throws DukeDescriptionException If index is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws BadDescriptionException, DukeDescriptionException {
        try {
            if (index.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }

            int done = Integer.parseInt(index);

            if (done <= 0 || done > taskList.getSize()) {
                throw new BadDescriptionException("Description for done cannot be "
                        + done);
            }

            taskList.getTask(done - 1).setDone(true);
            storage.writeListIntoFile(taskList.getList());

            return ui.say(SayType.DONE) + "\n  " + taskList.getTask(done - 1);
        } catch (NumberFormatException e) {
            throw new BadDescriptionException("Description for done cannot be Non-Integer");
        }
    }

    /**
     * Constructs a done command object.
     *
     * @param index The index of the task to be marked as done.
     */
    public DoneCommand(String index) {
        this.index = index;
    }
}
