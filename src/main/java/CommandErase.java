public class CommandErase extends Command {
    private int position;

    public CommandErase(int position) {
        this.position = position - 1;
    }

    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {
        Task selectTask = tasks.get(position);
        tasks.erase(position);

        String message = "Erase task: "
                + selectTask.getStatus();
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
