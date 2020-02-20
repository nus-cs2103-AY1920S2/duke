package duke.command;

import duke.exception.NoDateTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.UnableToSaveException;
import duke.main.Constant;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;
import duke.task.Task;

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
     * @return reply to user the event has been created
     * @throws NoDescriptionException If no name for deadline is specified.
     * @throws UnableToSaveException  If unable to save to storage.
     * @throws NoDateTimeException    If no date and time is specified.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws NoDescriptionException, UnableToSaveException, NoDateTimeException {
        int arrLength = inputArr.length;
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
        String saveReply = "Saving now....\n     ";
        saveReply += newE.toString();
        storage.saveToSave(tasks);
        return ui.reply(saveReply + "\n" + Constant.SPACE + "Aiyo still got " 
            + tasks.size() + " task(s), what you doing sia");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}