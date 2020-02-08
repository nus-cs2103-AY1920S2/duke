package duke.command;

import duke.main.Constant;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class FindCommand extends Command {
    public FindCommand(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * This method uses Ui to print all the tasks with the specified the keyword.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return reply to user the list of tasks specified
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String reply = getListWithKeyword(tasks);
        return ui.reply(reply);
    }

    private String getListWithKeyword(TaskList tasks) {
        String toFind = inputArr[1];
        String reply = "Here are the tasks with the keyword you asked for..";
        int numbering = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).name.indexOf(toFind) >= 0) {
                reply += ("\n  " + Constant.SPACE + numbering++ + ".");
                reply += (tasks.getTask(i));
            }
        }
        return reply;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}