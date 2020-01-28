package duke.command;

import duke.exception.*;
import duke.task.Todo;
import duke.task.Task;
import duke.main.*;

public class CreateTodoCommand extends Command {
    public CreateTodoCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    /**
     * This method creates a Todo and adds it to the tasklist specified in the
     * params. Ui sends a reply to the user, stating adding of new Todo is
     * successful.
     * 
     * @param tasks   Existing Tasklist
     * @param ui      Ui for user interaction
     * @param storage Storage to save tasks in local storage
     * @return nothing
     * @throws NoDescriptionException If no name for deadline is specified.
     * @throws UnableToSaveException  If unable to save to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException, UnableToSaveException{
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