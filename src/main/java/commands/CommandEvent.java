package commands;

import main.DukeProcessor;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandEvent implements DukeCommand{

    public void execute(DukeProcessor processor, String args) {
        String[] inputArgs = args.split(" ", 2)[1].split(" /at ");
        EventTask task = new EventTask(inputArgs[0], inputArgs[1]);

        List<Task> taskList = (ArrayList<Task>) processor.getTaskList();
        taskList.add(task);

        System.out.println("I've got it! Added the following task:");
        System.out.println(task);
        System.out.println("You've now got " + taskList.size() + " tasks in your list.");
    }
}
