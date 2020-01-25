import java.time.LocalDateTime;

public class Task_Deadline extends Task{

    private LocalDateTime time;

    public Task_Deadline(String description, LocalDateTime time){
        super(description);
        this.time=time;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString()+" (by: "+DateTimeUtil.standardFormat(time)+")";
    }
}
