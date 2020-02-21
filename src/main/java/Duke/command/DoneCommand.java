package Duke.command;

import Duke.TaskList;
import Duke.exception.DukeException;
import Duke.task.Task;

public class DoneCommand extends Command {
    Task taskDone;

    public DoneCommand(TaskList taskList, String taskDesc) {
        super(taskList, taskDesc);
    }

    @Override
    public String execute() throws DukeException {
        String in = taskDesc.split(" ", 2)[1];;;
        String out;
        try {
            int num = Integer.parseInt(in);
            taskDone = list.get(num - 1);
            taskDone.markDone(); //throws DukeException if task is already done
            stats.add(this);
            out = "Nice! I've marked this task as done:\n";
            out +=  "       " + taskDone;
        } catch (DukeException e) {
            throw e;
        } catch (IndexOutOfBoundsException e) { // when no int arg provided
            out = "☹ Oh no!!! Please input a valid number in the range of the task list to mark as done.\nUnsure? "
                    + "List the tasks out by typing the 'list' command to see your available tasks and their respective"
                    + " task number.";
            if (!list.isEmpty()) {
                out += (list.size() == 1) ? "\nAvailable range: 1" : "Available range from 1 to " + list.size();
            }
            throw new DukeException(out);

        } catch (NumberFormatException e) { // when non-int arg provided
            out = "☹ Oh no!!! Please input a valid INTEGER in the range of the task list to mark as done.\nUnsure? "
                    + "List the tasks out by typing the 'list' command to see your available tasks and their respective"
                    + " task number.";
            throw new DukeException(out);

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
