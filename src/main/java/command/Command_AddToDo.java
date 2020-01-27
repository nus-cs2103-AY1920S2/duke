package command;

import task.ToDo;

public class Command_AddToDo extends Command_AddTask{

    public Command_AddToDo(String description){
        super(new ToDo(description));
    }

}
