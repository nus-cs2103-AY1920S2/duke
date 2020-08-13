package duke.commands;

import duke.exceptions.DukeDuplicateTaskException;
import duke.exceptions.DukeWriteFailException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the command 'todo'.
 */
public class ToDoCommand extends Command {
    String taskName;

    /**
     * Constructor for ToDoCommand.
     * @param taskName name of task to be done.
     */
    public ToDoCommand(String taskName) {
        assert !taskName.isEmpty();
        this.taskName = taskName;
    }

    /**
     * Executes'todo' command.
     * @param taskList task list in running program.
     * @param ui ui handling running program.
     * @param storage storage handling running program.
     * @throws DukeWriteFailException if the program fails to write the modified data to the storage file.
     */
    public String execute(
            TaskList taskList, Ui ui, Storage storage) throws DukeWriteFailException, DukeDuplicateTaskException {
        ToDo t = new ToDo(taskName);
        if (!taskList.isDuplicate(t)) {
            taskList.addTask(t);
            storage.write(taskList);
            return Ui.setBorder("ADDED : \n\n" + t.toString() + "\n\n"
                    + "\tYou now have " + taskList.numTasks() + " item(s) on your list.");
        } else {
            throw new DukeDuplicateTaskException(t);
        }
    }

    /**
     * Compares and checks if commands are equal.
     * @param command object to compare command to.
     * @return boolean stating if this command and command are equal.
     */
    @Override
    public boolean equals(Object command) {
        if (command instanceof ToDoCommand) {
            return this.taskName.equals(((ToDoCommand) command).taskName);
        } else {
            return false;
        }
    }
}
