package duke.command;

import java.time.LocalDate;
import duke.task.*;
import duke.main.*;

public class ListCommand extends Command {
    public ListCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    /**
     * This method uses Ui to print tasks, depending on inputArr.
     * Case 1: No date specified, prints all tasks in tasklist
     * Case 2: Date specified, prints tasks with the specified date in tasklist.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return nothing
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String reply = "";

        if (inputArr.length == 1) {
            reply += "I told you save liao loh........";
            for (int i = 0; i < tasks.size(); i++) {
                int numbering = i + 1;
                reply += ("\n  " + Constant.SPACE + numbering + ".");
                reply += (tasks.getTask(i));
            }
        } else {
            reply += ("This are all the tasks with that date" + Constant.SPACE);
            String dateS = inputArr[1];
            LocalDate date = LocalDate.parse(dateS, Constant.FORMATTER_INPUT_DATE);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                if (currentTask instanceof Deadline || currentTask instanceof Event) {
                    if (currentTask.compareDate(date)) {
                        reply += ("\n  " + Constant.SPACE + numbering++ + ".");
                        reply += (currentTask);
                    }
                }
            }
        }
        ui.reply(reply);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}