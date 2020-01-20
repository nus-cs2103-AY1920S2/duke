package commands;

import main.DukeProcessor;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandList implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        List<Task> taskList = (ArrayList<Task>)processor.getTaskList();

        System.out.println("Here are the " + taskList.size() + " tasks I've noted down for you:");
        for(int i = 0; i < taskList.size(); i ++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i)));
        }
    }
}
