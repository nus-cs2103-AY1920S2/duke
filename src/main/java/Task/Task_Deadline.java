package Task;

public class Task_Deadline extends Task{

    private String time;

    public Task_Deadline(String description, String time){
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
