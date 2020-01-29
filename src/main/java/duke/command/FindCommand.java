package duke.command;
import java.io.IOException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

public class FindCommand extends Command{
    String keyword;
    public FindCommand(String keyword){
        this.keyword = keyword;
    }
    /**
     * Displays the list of tasks that have the keyword.
     * @param tasks The current task list.
     * @param ui The user interface to be updated.
     * @param storage The storage file to be updated.
     * @throws IOException If the storage file is not found.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException{
        ui.printList(tasks.findTasks(keyword));
    }
    /**
     * Returns the result of whether this is an exit program command.
     * @return The result of whether this command can exit the program.
     */
    public boolean isExit(){
        return false;
    }
}
