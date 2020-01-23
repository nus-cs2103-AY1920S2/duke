package command;

import task.Event;

public class Command_AddEvent extends Command_AddTask{

    public Command_AddEvent(String description, String time){
        super(new Event(description,time));
    }
}