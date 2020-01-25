package commands;

import exceptions.DukeException;
import main.DukeProcessor;
import tasks.Task;
import tasks.TodoTask;

import java.util.ArrayList;
import java.util.List;

public class CommandTodo extends CommandTask {

    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if(argsArray.length < 2) {
            throw new DukeException("Your 'todo' command is incorrect! Please follow the following format: todo " +
                    "<item>");
        }

        List<Task> taskList = (ArrayList<Task>) processor.getTaskList();
        TodoTask task = new TodoTask(argsArray[1]);
        taskList.add(task);

        System.out.println("I've got it! Added the following task:");
        System.out.println(task);

        super.execute(processor, args);
    }
}
