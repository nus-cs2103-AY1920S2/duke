package command;

import task.Event;

import java.time.LocalDate;

/**
 * Specific type of command that add event task to the storage.
 */
public class AddEventCommand extends AddTaskCommand {

    /**
     * Constructor for command that add a event task.
     * @param description contains the description of the event task.
     * @param time contains the event time of the task.
     */
    public AddEventCommand(String description, LocalDate time){
        super(new Event(description,time));
    }
}