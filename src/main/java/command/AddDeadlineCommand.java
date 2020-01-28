package command;

import task.Deadline;

import java.time.LocalDateTime;

/**
 * Specific type of command that add deadline task to the storage.
 */
public class AddDeadlineCommand extends AddTaskCommand {

    /**
     * Constructor for command that add a deadline task.
     * @param description contains the description of the deadline task.
     * @param time contains the deadline of the task.
     */
    public AddDeadlineCommand(String description, LocalDateTime time){
        super(new Deadline(description,time));
    }

}
