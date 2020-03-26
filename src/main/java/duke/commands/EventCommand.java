package duke.commands;

import duke.commands.exceptions.CommandException;
import duke.model.Storage;
import duke.model.TaskList;
import duke.tasks.Event;
import duke.tasks.Event;

/**
 *  The <code>EventCommand</code> class represents a event command which creates a
 *  new Event object and adds it to the task list.
 */
public class EventCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Added:\n";
    public static final String MESSAGE_EMPTY_CONTENT =
            "Event name and/or time not specified! Please enter in the format: event [name] /at [time]";
    public static final String MESSAGE_DUPLICATE_TODO =
            "Duplicate task not added:\n";
    
    private final String name;
    private final String time;
    
    public EventCommand(String content) {
        this.name = content.split(Event.TIME_DEMARCATOR)[0].strip();
        this.time = content.substring(name.length()).replace(Event.TIME_DEMARCATOR, "").strip();
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws CommandException {
        if (name.isEmpty() || time.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_CONTENT);
        }
        
        Event newEvent = new Event(name, time);
        
        try {
            taskList.add(newEvent);
            return MESSAGE_SUCCESS + newEvent;
        } catch (IllegalArgumentException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO + newEvent);
        }
    }
}
