package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;
import tasks.Task;
import tasks.TodoTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Command that handles creating and adding a "todo" task to the processor's TaskList.
 */
public class CommandTodo extends CommandTask {

    /**
     * Attempts to create a "todo" task before adding it to the processor's TaskList.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException Throws an exception if the input format is incorrect.
     */
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        if (argsArray.length < 2) {
            throw new DukeException("Your 'todo' command is incorrect! Please follow the following format: todo "
                    + "<item>");
        }

        TodoTask task = new TodoTask(argsArray[1]);
        processor.getTaskList().add(task);

        Ui.print("I've got it! Added the following task:");
        Ui.print(task.toString());

        super.execute(processor, args);
    }
}
