package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.DeadlineTask;
import tasks.Task;
import tasks.TodoTask;

import java.util.ArrayList;
import java.util.List;

public class CommandDeadline implements DukeCommand {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /by ");
        if(!args.contains(" /by ") || inputArgs.length < 2) {
            throw new DukeException("Your deadline command is incorrect! Please follow the format: deadline <item> " +
                    "/by <time>");
        }

        DeadlineTask task = new DeadlineTask(inputArgs[0], inputArgs[1]);

        List<Task> taskList = (ArrayList<Task>) processor.getTaskList();
        taskList.add(task);

        System.out.println("I've got it! Added the following task:");
        System.out.println(task);
        System.out.println("You've now got " + taskList.size() + " tasks in your list.");

    }
}
