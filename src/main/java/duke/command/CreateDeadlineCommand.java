package duke.command;

import duke.exception.*;
import duke.main.*;
import duke.task.Deadline;
import duke.task.Task;

public class CreateDeadlineCommand extends Command {
    public CreateDeadlineCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    /**
     * This method creates a Deadline and adds it to the tasklist specified in the
     * params. Ui sends a reply to the user, stating adding of new Deadline is
     * successful.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return nothing
     * @throws NoDescriptionException If no name for deadline is specified.
     * @throws UnableToSaveException  If unable to save to storage.
     * @throws NoDateException        If no date is specified.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException, UnableToSaveException, NoDateException{
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