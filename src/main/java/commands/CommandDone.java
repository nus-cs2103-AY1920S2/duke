package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;
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
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'done' command is incorrect! Use the following format: done <number>");
        }
        int taskNumber = Integer.parseInt(argsArray[1]);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().get(taskIndex);
        selectedTask.complete();

        Ui.print("Great job on being productive! I've marked the following task as completed:");
        Ui.print(selectedTask.toString());

        try {
            Storage.saveTasks(processor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
