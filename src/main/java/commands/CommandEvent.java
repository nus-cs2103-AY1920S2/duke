package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandEvent implements DukeCommand{

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /at ");

        if(!args.contains(" /at ") || inputArgs.length < 2) {
            throw new DukeException("Your 'event' command is incorrect! Please follow the format: event <item> " +
                    "/at <time>");
        }


        EventTask task = new EventTask(inputArgs[0], inputArgs[1]);

        List<Task> taskList = (ArrayList<Task>) processor.getTaskList();
        taskList.add(task);

        System.out.println("I've got it! Added the following task:");
        System.out.println(task);
        System.out.println("You've now got " + taskList.size() + " tasks in your list.");
    }
}
