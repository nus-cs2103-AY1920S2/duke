public class Deadline extends Task {
    String day;
    public Deadline(String description, String day){
        super (description);
        this.day = day;
    }

    @Override
    public String toString(){
        return "[D]" + getStatusIcon() + description + "(by: " + day + ")";
    }
}
