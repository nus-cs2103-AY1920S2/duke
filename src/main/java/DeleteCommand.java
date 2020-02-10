public class DeleteCommand extends Command {
    Task taskDeleted;

    public DeleteCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    public String execute() {
        String out;
        try {
            String taskNum = taskDesc.split(" ", 2)[1];
            Task currTask = list.get(Integer.parseInt(taskNum) - 1);
            list.remove(currTask);
            taskDeleted = currTask;
            stats.add(this);
            out = "Noted. I've removed this task:\n" +  currTask + "\nNow you have " + list.size()
                    + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            out = "☹ OOPS!!! Please input a valid number in the range of the task list to delete.";
        } catch (NumberFormatException e) { // when non-int arg provided
            out = "OOPS!!! Delete must take a valid integer in the range of the task list.";
        } finally {
            storage.saveTask(list);
            statStorage.saveStats(stats);
        }
        return out;
    }

    public String toString() {
        return taskDesc;
    }
}
