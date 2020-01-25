package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;
import tasks.DeadlineTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandDeadline extends CommandTask {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /by ");
        if(!args.contains(" /by ") || inputArgs.length < 2) {
            throw new DukeException("Your deadline command is incorrect! Please follow the format: deadline <item> " +
                    "/by <time>");
        }

        DeadlineTask task = new DeadlineTask(inputArgs[0], inputArgs[1]);
        processor.getTaskList().add(task);

        Ui.print("I've got it! Added the following task:");
        Ui.print(task.toString());

        super.execute(processor, args);
    }
}
