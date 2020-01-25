package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;
import tasks.Task;

public class CommandDelete implements Command {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if(argsArray.length < 2) {
            throw new DukeException("Your 'delete' command is incorrect! Use the following format: delete <number>");
        } else if(Integer.parseInt(argsArray[1]) > processor.getTaskList().size() - 1 || Integer.parseInt(argsArray[1]) < 0) {
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
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
