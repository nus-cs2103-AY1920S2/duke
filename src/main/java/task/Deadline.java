package task;

public class Deadline extends Task{

    private String time;

    public Deadline(String description, String time){
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
