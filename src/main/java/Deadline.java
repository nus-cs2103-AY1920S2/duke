public class Deadline extends Task {
    public String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString(){
        return super.toString() + "(by: " + date + ")";
    }
}
