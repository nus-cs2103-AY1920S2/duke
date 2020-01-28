package duke.command;

import java.io.IOException;
import duke.main.*;

public class DoneCommand extends Command {
    public DoneCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        int taskNo = Integer.parseInt(inputArr[1]) - 1;
        tasks.setDone(taskNo);
        storage.saveToSave(tasks);
        ui.reply("Okcan, I mark this task as done:\n" + Constant.SPACE + tasks.getTask(taskNo));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}