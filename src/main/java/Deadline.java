public class Deadline extends Task implements java.io.Serializable{

    protected String by;

    public Deadline (String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
