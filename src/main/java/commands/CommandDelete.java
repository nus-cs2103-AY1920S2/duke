package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import tasks.Task;

/**
 * Handles the deletion of tasks from the processor's TaskList.
 */
public class CommandDelete implements Command {

    /**
     * Deletes the task from the list and updates the list thereafter.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException Throws an exception if the input format is incorrect, or if the task at the index is not
     *                       found
     */
    public String execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);

        int taskNumber = Integer.parseInt(argsArray[1]);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().removeAt(taskIndex);

        try {
            Storage.saveTasks(processor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String output = String.format("%s\n%s\n%s\n",
                "Noted! I've deleted the following task:",
                selectedTask.toString(),
                "You now have " + processor.getTaskList().size() + " tasks remaining!");

        return output;
    }
}
