public class Deadline extends Task {
    protected String day;
    public Deadline(String description, String day){
        super (description);
        this.day = day;
    }

    public String toStringTaskstxt(){
        return "D/" + getStatusIconInBin() + "/" + description + "/" + day + "\n";
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + day + ")";
    }
}
