package commands;
import storage.Storage;
import task.Event;
import taskList.TaskList;

import java.time.LocalDate;

/**
 * Class definition for the "event" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final Event ev;

    /**
     * Convenience constructor using raw values.
     *
     * @param date the LocalDate object that references the event day.
     * @param desc the desc that represents the action/activity of the Event object.
     */
    public EventCommand(String desc,
                           LocalDate date){
        this.ev = new Event(desc, date);
    }

    /**
     * Constructor using a complete Event object.
     *
     * @param ev the Event object to be acted upon.
     */
    public EventCommand(Event ev) {
        this.ev = ev;
    }

    public Event getEvent() {
        return ev;
    }

    /**
     * Executes the command.
     *
     * @param tasks the tasks that is currently being referenced in Duke.
     * @param storage the storage tool for loading and saving the state of the list after every command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.add(ev);
        System.out.println("Successfully added: " + ev);
        System.out.println("You now have " + (tasks.getList().size()) + " number of tasks in the list");
        storage.save(tasks);
        //return new CommandResult(String.format("Successfully added:", dl));
    }
}

