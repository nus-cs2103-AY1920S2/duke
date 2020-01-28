package duke.command;

import duke.*;
import duke.task.*;

import java.io.IOException;

/**
 * Class which consists of Tasks which adds to the List
 * Eg: Deadline, TO-DO, Event
 */
public class AddCommand extends Command {


    /**
     * Instantiates a new Add command.
     *
     * @param user_input the user input
     */
    public AddCommand(String user_input) {
        super(user_input);
    }


    /**
     * Overwrites the execute method from Abstract class execute.
     *
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
        if (user_input.contains("deadline")) {
            Deadline new_deadLine = new Deadline(user_input);
            new_deadLine.setDescription(user_input);
            new_deadLine.setBy(new_deadLine.format_tasks(user_input));
            new_deadLine.setD1();
            taskList.add_to_list(new_deadLine);
            storage.saveTask(new_deadLine);
            //  deadline_event_hash.addToHashMap(new_deadLine.d1.toLocalDate().toString(), new_deadLine);
            ui.printTasks(new_deadLine, taskList.getList());
        } else if (user_input.contains("todo")) {
            Task new_todo_task = new Todo(user_input);
            new_todo_task.setDescription(new_todo_task.format_tasks("todo"));
            taskList.add_to_list(new_todo_task);
            storage.saveTask(new_todo_task);
            ui.printTasks(new_todo_task, taskList.getList());
        } else if (user_input.contains("event")) {
            Event new_event = new Event(user_input);
            new_event.setDescription(user_input);
            new_event.setAt(new_event.format_tasks(user_input));
            new_event.setD1();
            taskList.add_to_list(new_event);
            //deadline_event_hash.addToHashMap(new_event.d1.toLocalDate().toString(), new_event);
            storage.saveTask(new_event);
            ui.printTasks(new_event, taskList.getList());
        }
    }

}
