package duke.command;

import duke.task.Todo;
import duke.task.Task;
import duke.exception.NoDescriptionException;
import duke.exception.UnableToSaveException;
import duke.main.Constant;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class CreateTodoCommand extends Command {
    public CreateTodoCommand(String[] inputArr) {
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
     * @return reply to user the todo has been created
     * @throws NoDescriptionException If no name for deadline is specified.
     * @throws UnableToSaveException  If unable to save to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NoDescriptionException, UnableToSaveException {
        String saveReply = "Saving now....\n     ";
        int arrLength = inputArr.length;
        String nameOfEvent = combineString(inputArr, 1, arrLength);
        if (nameOfEvent.equals("")) {
            throw new NoDescriptionException();
        }
        Task newT = new Todo(nameOfEvent);
        tasks.add(newT);
        saveReply += newT.toString();
        storage.saveToSave(tasks);
        return ui.reply(saveReply + "\n" + Constant.SPACE + "Aiyo still got " 
            + tasks.size() + " task(s), what you doing sia");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}