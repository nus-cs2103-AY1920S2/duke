package command;

import task.Deadline;

public class Command_AddDeadline extends Command_AddTask{

    public Command_AddDeadline(String description, String time){
        super(new Deadline(description,time));
    }

}
