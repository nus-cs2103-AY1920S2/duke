public class DoneCommand extends Command {

    int doneIndex;

    public DoneCommand(int index) {
        doneIndex = index;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (taskList.DoneTask(doneIndex))
            storage.SaveTaskListToFile(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
