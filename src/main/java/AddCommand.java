public class AddCommand extends Command {
    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputTokens = command.split(" ");
        Task task = null;
        switch (inputTokens[0]) {
            case "todo":
                // Add a To-do task
                task = tasks.makeNewTodoTask(command);
                tasks.add(task);
                break;
            case "deadline":
                // Add a Deadline task
                task = tasks.makeNewDeadlineTask(command);
                tasks.add(task);
                break;
            case "event":
                // Add an Event task
                task = tasks.makeNewEventTask(command);
                tasks.add(task);
                break;
        }
        if (task == null) {
            throw new DukeException("☹ OOPS!!! Task could not be added!");
        }
        if (!storage.save(tasks)) {
            throw new DukeException("☹ OOPS!!! Failed to save list!");
        }
        ui.showAddTask(task, tasks.size());
    }
}
