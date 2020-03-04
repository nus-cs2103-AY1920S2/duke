package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import tasks.TodoTask;


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
    public String execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);

        TodoTask task = new TodoTask(argsArray[1]);
        processor.getTaskList().add(task);

        String output = String.format("%s\n%s\n%s\n", "I've got it! Added the following task:",
                task.toString(),
                "You've now got " + processor.getTaskList().size() + " tasks in your list.");

        super.execute(processor, args);
        return output;
    }
}
