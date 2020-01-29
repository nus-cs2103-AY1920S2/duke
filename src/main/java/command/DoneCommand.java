package command;

import common.Message;
import common.Storage;
import ui.TextUi;
import exception.DukeException;
import task.*;

/**
 * Represents a done command that set a Task to done.
 */
public class DoneCommand extends Command {

    protected int index;

    public DoneCommand(int index){
        super();
        this.index = index - 1;
    }

    public void execute(TaskList tasks, TextUi textUi, Storage storage) throws DukeException {
        if(this.index >= tasks.getList().size() || this.index < 0){
            textUi.showError(Message.MESSAGE_INVALIDCOMMAND);
            return;
        }
        tasks.done(this.index);
        textUi.showDoneTask(this.index, tasks);
        storage.writeToFile(tasks.getList());
    }

    public boolean isExit(){
        return false;
    }
}
