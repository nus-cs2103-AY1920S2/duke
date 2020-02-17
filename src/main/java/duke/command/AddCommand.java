package duke.command;


import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


import java.io.IOException;

/**
 * Class which consists of Tasks which adds to the List
 * Eg: Deadline, TO-DO, Event.
 */
public class AddCommand extends Command {


    // This is from master
    /**
     * Instantiates a new Add command.
     *
     * @param userInput the user input
     */
    public AddCommand(String userInput) {
        super(userInput);
    }


    /**
     * Overwrites the execute method from Abstract class execute.
     * Check against the user's input then pass it to its respective task class.
     *
     * @param storage  Deals with loading tasks from file.
     * @param ui       Deals with interactions with the user
     * @param taskList List containing all the tasks
     * @throws DukeException Main exception method I have created
     * @throws IOException   For any potential Input/Output exceptions from incorrect file
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList)
            throws DukeException, IOException {

        if (userInput.contains("deadline")) {
            assert (userInput.contains("deadline")) : "userInput should contain deadline!";
            Deadline newDeadLine = new Deadline(userInput);
            newDeadLine.setDescription(userInput);
            newDeadLine.setBy(newDeadLine.formatTasks(userInput));
            newDeadLine.setD1();
            taskList.addToList(newDeadLine);
            storage.saveTask(newDeadLine);
            return ui.printTasks(newDeadLine, taskList.getList(), storage);
        } else if (userInput.contains("todo")) {
            assert (userInput.contains("todo")) : "userInput should contain todo!";
            Task newTodoTask = new Todo(userInput);
            newTodoTask.setDescription(newTodoTask.formatTasks("todo"));
            taskList.addToList(newTodoTask);
            storage.saveTask(newTodoTask);
            return ui.printTasks(newTodoTask, taskList.getList(), storage);
        } else if (userInput.contains("event")) {
            assert (userInput.contains("event")) : "userInput should contain event!";
            Event newEvent = new Event(userInput);
            newEvent.setDescription(userInput);
            newEvent.setAt(newEvent.formatTasks(userInput));
            newEvent.setD1();
            taskList.addToList(newEvent);
            storage.saveTask(newEvent);
            return ui.printTasks(newEvent, taskList.getList(), storage);
        } else {
            return ui.invalidAddTaskException();
        }
    }

}
