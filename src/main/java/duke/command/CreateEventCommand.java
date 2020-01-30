package duke.command;

import duke.exception.*;
import duke.task.Event;
import duke.task.Task;
import duke.main.*;

public class CreateEventCommand extends Command {
    public CreateEventCommand(String[] inputArr) {
        this.inputArr = inputArr;
    }

    /**
     * This method creates a Event and adds it to the tasklist specified in the
     * params. Ui sends a reply to the user, stating adding of new Event is
     * successful.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return nothing
     * @throws NoDescriptionException If no name for deadline is specified.
     * @throws UnableToSaveException  If unable to save to storage.
     * @throws NoDateTimeException    If no date and time is specified.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws NoDescriptionException, UnableToSaveException, NoDateTimeException {
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