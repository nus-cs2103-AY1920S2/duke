package duke.command;

import duke.other.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String instruction;
    private String[] replyArr;

    public FindCommand(String instruction, String[] replyArr) {
        this.instruction = instruction;
        this.replyArr = replyArr;
    }

    /**
     * Handles the Find command.
     * @param taskList Overall TaskList of all the Tasks
     * @param ui       Overall Ui handling the ui of Duke
     * @return Returns the response of the bot to this command
     */
    public String execute(TaskList taskList, Ui ui) {
        return taskList.findTaskByKeyword(replyArr);
    }

}
