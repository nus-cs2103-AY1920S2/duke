package duke.command;

import duke.other.*;
import duke.task.TaskList;


public class DeleteCommand extends Command{
    private String instruction;
    private String[] replyArr;

    public DeleteCommand (String instruction, String[] replyArr) {
        this.instruction = instruction;
        this.replyArr = replyArr;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteInstruction(replyArr);
    }




}
