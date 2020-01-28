import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] inputArr){
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoNumberDeleteException, NoSuchDeleteException, IOException{
        if (inputArr.length < 2) {
            throw new NoNumberDeleteException();
        } else {
            int taskToDelete = Integer.parseInt(inputArr[1]);
            if (taskToDelete > tasks.size()) {
                throw new NoSuchDeleteException();
            } else {
                String whichTaskDelete = tasks.getTask(taskToDelete - 1).toString();
                tasks.removeTask(taskToDelete - 1);
                storage.saveToSave(tasks);
                ui.reply("Okcan. I will remove this task:\n" + Constant.SPACE + "  " + whichTaskDelete + "\n" + Constant.SPACE
                        + "But you still have " + tasks.size() + " task(s) in the list.");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}