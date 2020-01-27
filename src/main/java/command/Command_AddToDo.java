package command;

import task.ToDo;

/**
 * Specific type of command that add to do task to the storage.
 */
public class Command_AddToDo extends Command_AddTask{

    /**
     * Constructor for command that add a to do task.
     * @param description contains the description of the to do task.
     */
    public Command_AddToDo(String description){
        super(new ToDo(description));
    }

}
