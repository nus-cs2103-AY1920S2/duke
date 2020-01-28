package command;

import java.io.IOException;
import exception.*;
import main.*;
import task.Deadline;
import task.Task;

public class CreateDeadlineCommand extends Command {
    public CreateDeadlineCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException, IOException, NoDateException{
        int arrLength = inputArr.length;
        String saveReply = "Saving now....:\n     ";
        int pointer = findIndex("/by", inputArr);
        if (pointer == -1 || pointer == arrLength - 1) {
            throw new NoDateException();
        }
        String nameOfEvent = combineString(inputArr, 1, pointer);
        if (nameOfEvent.equals("")) {
            throw new NoDescriptionException();
        }
        String date = combineString(inputArr, pointer + 1, arrLength);
        Task newD = new Deadline(nameOfEvent, date);
        tasks.add(newD);
        saveReply += newD.toString();
        storage.saveToSave(tasks);
        ui.reply(saveReply + "\n" + Constant.SPACE + "Aiyo still got " + tasks.size() + " task(s), what you doing sia");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}