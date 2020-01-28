package duke.command;

import java.io.IOException;
import duke.exception.*;
import duke.task.Todo;
import duke.task.Task;
import duke.main.*;

public class CreateTodoCommand extends Command {
    public CreateTodoCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException, IOException{
        int arrLength = inputArr.length;
        String saveReply = "Saving now....:\n     ";
        String nameOfEvent = combineString(inputArr, 1, arrLength);
        if(nameOfEvent.equals("")){
            throw new NoDescriptionException();
        }
        Task newT = new Todo(nameOfEvent);
        tasks.add(newT);
        saveReply += newT.toString();
        storage.saveToSave(tasks);
        ui.reply(saveReply + "\n" + Constant.SPACE + "Aiyo still got " + tasks.size() + " task(s), what you doing sia");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}