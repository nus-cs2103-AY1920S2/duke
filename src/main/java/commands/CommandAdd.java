package commands;

import main.DukeProcessor;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class CommandAdd implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        List<Task> textList = (ArrayList<Task>) processor.getTaskList();
        textList.add(new Task(args));

        System.out.println(String.format("added: %s", args));
    }
}
