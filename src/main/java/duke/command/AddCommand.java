package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.Ui;
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
     * @param storage Deals with loading tasks from file.
     * @param ui Deals with interactions with the user
     * @param taskList List containing all the tasks
     * @throws DukeException Main exception method I have created
     * @throws IOException For any potential Input/Output exceptions from incorrect file
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException, IOException {
        if (userInput.contains("deadline")) {
            Deadline newDeadLine = new Deadline(userInput);
            newDeadLine.setDescription(userInput);
            newDeadLine.setBy(newDeadLine.formatTasks(userInput));
            newDeadLine.setD1();
            taskList.addToList(newDeadLine);
            storage.saveTask(newDeadLine);
            //  deadline_event_hash.addToHashMap(newDeadLine.d1.toLocalDate().toString(), newDeadLine);
            ui.printTasks(newDeadLine, taskList.getList());
        } else if (userInput.contains("todo")) {
            Task newTodoTask = new Todo(userInput);
            newTodoTask.setDescription(newTodoTask.formatTasks("todo"));
            taskList.addToList(newTodoTask);
            storage.saveTask(newTodoTask);
            ui.printTasks(newTodoTask, taskList.getList());
        } else if (userInput.contains("event")) {
            Event newEvent = new Event(userInput);
            newEvent.setDescription(userInput);
            newEvent.setAt(newEvent.formatTasks(userInput));
            newEvent.setD1();
            taskList.addToList(newEvent);
            //deadline_event_hash.addToHashMap(newEvent.d1.toLocalDate().toString(), newEvent);
            storage.saveTask(newEvent);
            ui.printTasks(newEvent, taskList.getList());
        }
    }

}
