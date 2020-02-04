package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import tasks.Task;

/**
 * Command that handles marking a task as done in the TaskList.
 */
public class CommandDone implements Command {

    /**
     * Attempts to find the task selected by the user, then marks it as done, saving thereafter.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException Throws exception if Duke is unable to find a task at the index specified.
     */
    public String execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'done' command is incorrect! Use the following format: done <number>");
        } else if (Integer.parseInt(argsArray[1]) > processor.getTaskList().size()) {
            throw new DukeException("You've selected a non-existent task to complete! Please try again!");
        } else if (Integer.parseInt(argsArray[1]) < 0) {
            throw new DukeException("You've entered an index below the number of tasks in the list! Please try again!");
        }
        int taskNumber = Integer.parseInt(argsArray[1]);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().get(taskIndex);
        selectedTask.complete();

        try {
            Storage.saveTasks(processor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String output = String.format("%s\n%s\n", "Great job on being productive! I've marked the following task as "
                + "completed:",
                selectedTask.toString());

        return output;
    }
}
