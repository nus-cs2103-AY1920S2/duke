package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;
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
     *      found
     */
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'delete' command is incorrect! Use the following format: delete <number>");
        } else if (Integer.parseInt(argsArray[1]) > processor.getTaskList().size() - 1 || Integer.parseInt(argsArray[1])
                < 0) {
            throw new DukeException("You've selected a non-existent task to delete! Please try again!");
        }

        int taskNumber = Integer.parseInt(argsArray[1]);
        int taskIndex = taskNumber - 1;

        Task selectedTask = processor.getTaskList().removeAt(taskIndex);
        Ui.print("Noted! I've deleted the following task:");
        Ui.print(selectedTask.toString());
        Ui.print("You now have " + processor.getTaskList().size() + " tasks remaining!");

        try {
            Storage.saveTasks(processor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
