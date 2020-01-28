package command;

import task.ToDo;

/**
 * Specific type of command that add to do task to the storage.
 */
public class AddToDoCommand extends AddTaskCommand {

    /**
     * Constructor for command that add a to do task.
     * @param description contains the description of the to do task.
     */
    public AddToDoCommand(String description){
        super(new ToDo(description));
    }

}
