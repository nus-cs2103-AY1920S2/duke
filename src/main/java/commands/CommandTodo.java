package commands;

import main.DukeProcessor;
import tasks.Task;
import tasks.TodoTask;

import java.util.ArrayList;
import java.util.List;

public class CommandTodo implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        String[] argsArray = args.split(" ", 2);

        List<Task> taskList = (ArrayList<Task>) processor.getTaskList();
        TodoTask task = new TodoTask(argsArray[1]);
        taskList.add(task);

        System.out.println("I've got it! Added the following task:");
        System.out.println(task);
        System.out.println("You've now got " + taskList.size() + " tasks in your list.");
    }
}
