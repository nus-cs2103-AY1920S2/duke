package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;
import tasks.EventTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandEvent extends CommandTask{

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /at ");

        if(!args.contains(" /at ") || inputArgs.length < 2) {
            throw new DukeException("Your 'event' command is incorrect! Please follow the format: event <item> " +
                    "/at <time> to <time>");
        }

        EventTask task = new EventTask(inputArgs[0], inputArgs[1]);
        processor.getTaskList().add(task);

        Ui.print("I've got it! Added the following task:");
        Ui.print(task.toString());

        super.execute(processor, args);
    }
}
