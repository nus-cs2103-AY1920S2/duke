package duke.commands;

import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        assert !keyword.isEmpty();
        this.keyword = keyword;
    }

    /**
     * Executes 'find' command.
     * @param taskList task list in running program.
     * @param ui ui handling running program.
     * @param storage storage handling running program.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList results = taskList.search(keyword);
        String toPrint = "";
        if (results.numTasks() > 0) {
            toPrint += "Here are the matching tasks in your list!!\n";
            for (int i = 0; i < results.numTasks(); i++) {
                toPrint += "(" + (i + 1) + ") " + results.getList().get(i) + "\n";
            }
        } else if (results.numTasks() == 0) {
            toPrint += "There are no matching results in your list.";
        } else if (taskList.numTasks() == 0) {
            toPrint += "You have nothing on your list.";
        }
        return Ui.setBorder(toPrint);
    }

    /**
     * Compares and checks if commands are equal.
     * @param command object to compare command to.
     * @return boolean stating if this command and command are equal.
     */
    @Override
    public boolean equals(Object command) {
        return command instanceof FindCommand;
    }
}
