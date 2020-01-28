package command;

import java.io.IOException;
import exception.*;
import task.Event;
import task.Task;
import main.*;

public class CreateEventCommand extends Command {
    public CreateEventCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException, IOException, NoDateTimeException {
        int arrLength = inputArr.length;
        String saveReply = "Saving now....:\n     ";
        int pointer = findIndex("/at", inputArr);
        if (pointer == -1 || pointer == arrLength - 1) {      
            throw new NoDateTimeException();
        } 
        String nameOfEvent = combineString(inputArr, 1, pointer);
        if (nameOfEvent.equals("")) {
            throw new NoDescriptionException();
        }
        String dateTime = combineString(inputArr, pointer + 1, arrLength);
        Task newE = new Event(nameOfEvent, dateTime);
        tasks.add(newE);
        saveReply += newE.toString();
        storage.saveToSave(tasks);
        ui.reply(saveReply + "\n" + Constant.SPACE + "Aiyo still got " + tasks.size() + " task(s), what you doing sia");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}