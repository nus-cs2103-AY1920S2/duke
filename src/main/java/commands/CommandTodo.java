package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;
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

        TodoTask task = new TodoTask(argsArray[1]);
        processor.getTaskList().add(task);

        Ui.print("I've got it! Added the following task:");
        Ui.print(task.toString());

        super.execute(processor, args);
    }
}
