package task;

import java.time.LocalDateTime;

public class Deadline extends Task{

    private LocalDateTime time;

    public Deadline(String description, LocalDateTime time){
        super(description);
        this.time=time;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString()+" (by: "+time+")";
    }
}
