public class Command_AddDeadline extends Command_AddTask{

    public Command_AddDeadline(String description, String time){
        super(new Task_Deadline(description,time));
    }

}
