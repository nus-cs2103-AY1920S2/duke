public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getWhen(){
        return "(by: " + this.by + ")";

    }
    public String extraInfo(){
        return this.by;
    }
}
