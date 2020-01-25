import java.time.LocalDateTime;

public class Command_AddEvent extends Command_AddTask{

    public Command_AddEvent(String description, LocalDateTime time){
        super(new Task_Event(description,time));
    }
}
