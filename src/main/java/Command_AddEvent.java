public class Command_AddEvent extends Command_AddTask{

    public Command_AddEvent(String description, String time){
        super(new Task_Event(description,time));
    }
}
