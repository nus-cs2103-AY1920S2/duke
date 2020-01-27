package Command;

import Task.Task_ToDo;

public class Command_AddToDo extends Command_AddTask{

    public Command_AddToDo(String description){
        super(new Task_ToDo(description));
    }

}
