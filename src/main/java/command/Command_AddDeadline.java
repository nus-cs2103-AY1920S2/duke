package command;

import task.Deadline;

import java.time.LocalDateTime;

public class Command_AddDeadline extends Command_AddTask{

    public Command_AddDeadline(String description, LocalDateTime time){
        super(new Deadline(description,time));
    }

}
