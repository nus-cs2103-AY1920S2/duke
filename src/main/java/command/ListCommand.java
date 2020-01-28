package command;

import java.time.LocalDate;
import task.*;
import main.*;

public class ListCommand extends Command {
    public ListCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String reply = "";

        if (inputArr.length == 1) {
            for (int i = 0; i < tasks.size(); i++) {
                int numbering = i + 1;
                reply += (numbering + ".");
                reply += (tasks.getTask(i) + "\n" + Constant.SPACE);
            }
            reply += "\n" + Constant.SPACE + "I told you save liao loh........";
        } else {
            String dateS = inputArr[1];
            LocalDate date = LocalDate.parse(dateS, Constant.FORMATTER_INPUT_DATE);
            int numbering = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                if (currentTask instanceof Deadline || currentTask instanceof Event) {
                    if (currentTask.compareDate(date)) {
                        reply += (numbering++ + ".");
                        reply += (currentTask + "\n" + Constant.SPACE);
                    }
                }
            }
            reply += ("\n" + Constant.SPACE + "This are all the tasks with that date");
        }
        ui.reply(reply);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}