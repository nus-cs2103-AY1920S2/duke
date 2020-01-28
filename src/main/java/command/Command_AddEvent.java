package command;

import task.Event;

import java.time.LocalDateTime;

public class Command_AddEvent extends Command_AddTask{


    public Command_AddEvent(String description, LocalDateTime time){
        super(new Event(description,time));
    }
}