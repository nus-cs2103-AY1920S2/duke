package duke.command;

import duke.task.TaskList;

import duke.other.Storage;
import duke.other.Ui;


/**
 * Represents a command that prints out information from the TaskList that extends the Command class. A ShowCommand
 * object corresponds to a command type represented by a String followed by the necessary details of the command (e.g.
 * done 2).
 */
public class ShowCommand extends Command {
    private String instruction;
    private String[] replyArr;

    public ShowCommand(String instruction, String[] replyArr) {
        this.instruction = instruction;
        this.replyArr = replyArr;
    }

    /**
     * Reads the command type ("instruction") and executes the respective methods to handle the command.
     *
     * @param taskList Overall TaskList of all the Tasks
     * @param ui       Overall Ui handling the ui of Duke
     * @param storage  Storage handling the storage of the Tasks in TaskList
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (instruction) {
        case "date":
            taskList.showTaskOnDate(replyArr);
            break;
        case "list":
            taskList.listTasks();
            break;
        case "done":
            taskList.markTaskAsDone(replyArr);
            break;
        default:
            System.out.println("    Sorry! I don't understand what is " + instruction);
        }
    }
}
