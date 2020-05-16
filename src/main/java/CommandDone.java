public class CommandDone extends Command {
    private int taskNumber;

    public CommandDone(int taskNumber) {
        this.taskNumber = taskNumber - 1;
    }

    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {
        Task selectTask = tasks.get(taskNumber);
        selectTask.setDone(true);
        String message = "Mark as done: " + selectTask.getDisplayName();
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
