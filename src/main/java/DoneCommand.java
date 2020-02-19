public class DoneCommand extends Command {
    Task taskDone;

    public DoneCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    @Override
    public String execute() {
        String in = taskDesc;
        String out;
        try {
            int num = Integer.parseInt(in.substring(5));
            taskDone = list.get(num - 1);
            taskDone.markDone();
            stats.add(this);
            out = "Nice! I've marked this task as done:\n";
            out +=  "       " + taskDone;
        } catch (IndexOutOfBoundsException e) { // when no int arg provided
            out = "OOPS!!! Done must take a valid number in the range of the task list.";
        } catch (NumberFormatException e) { // when non-int arg provided
            out = "OOPS!!! Done must take a valid integer in the range of the task list.";
        } finally {
            storage.saveTask(list);
            statStorage.saveStats(stats);
        }
        return out;
    }

    public String toString() {
        return "Done task: " + taskDone;
    }

}
