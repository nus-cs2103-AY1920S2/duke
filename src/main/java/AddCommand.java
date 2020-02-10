public class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        int oldNum = tasks.getTaskList().size();
        if (checkDuplicate(tasks, task)) {
            ui.printDuplicate(task);
        } else {
            tasks.getTaskList().add(task);
            assert tasks.getTaskList().size() + 1 == oldNum : "Task added incorrectly";
            ui.printAdd(task, tasks);
        }
    }

    public boolean checkDuplicate(TaskList tasks, Task task) {
        boolean bool = false;
        for (Task t : tasks.getTaskList()) {
            if (t.getTaskName().equals(task.getTaskName())) {
                bool = true;
            }
        }
        return bool;
    }

    public boolean isExit() {
        return false;
    }
}
