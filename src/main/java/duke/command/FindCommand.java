package duke.command;

import duke.main.*;

public class FindCommand extends Command {
    public FindCommand(String[] inputArr) {
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String toFind = inputArr[1];
        String reply = "Here are the tasks with the keyword you asked for..";
        int numbering = 1;
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.getTask(i).name.indexOf(toFind) >= 0){
                reply += ("\n  " + Constant.SPACE + numbering++ + ".");
                reply += (tasks.getTask(i));
            }
        }
        ui.reply(reply);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}