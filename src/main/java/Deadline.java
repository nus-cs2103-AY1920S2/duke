public class Deadline extends Task {
    String by;
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
