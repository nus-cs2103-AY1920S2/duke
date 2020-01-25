package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandTask implements DukeCommand {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        List<Task> taskList = (ArrayList<Task>) processor.getTaskList();
        System.out.println("You've now got " + taskList.size() + " tasks in your list.");

        try {
            processor.getTaskListHandler().saveTasks();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
