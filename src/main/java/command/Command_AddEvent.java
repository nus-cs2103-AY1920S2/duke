package command;

import task.Event;

import java.time.LocalDateTime;

/**
 * Specific type of command that add event task to the storage.
 */
public class Command_AddEvent extends Command_AddTask{

    /**
     * Constructor for command that add a event task.
     * @param description contains the description of the event task.
     * @param time contains the event time of the task.
     */
    public Command_AddEvent(String description, LocalDateTime time){
        super(new Event(description,time));
    }
}